/*
 * BookFinderBean.java
 *
 * Created on 18 maj 2007, 16:13
 */

package edu.uj.elms.logic;

import edu.uj.elms.beans.Book;
import edu.uj.elms.beans.BookCopy;
import edu.uj.elms.beans.BookCopy.State;
import edu.uj.elms.beans.ReservationDetails;
import edu.uj.elms.beans.SearchPattern;
import edu.uj.elms.converters.BookConverter;
import edu.uj.elms.converters.BookCopyConverter;
import edu.uj.elms.entities.BookCopyEntity;
import edu.uj.elms.entities.BookEntity;
import edu.uj.util.database.Enumerator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Finds books.
 * @author Piotrek
 */
@Stateless
public class BookFinderBean implements BookFinderLocal {

    @EJB
    private QueuePlaceCounterLocal queuePlaceCounterBean;
    
    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of BookFinderBean */
    public BookFinderBean() {
    }
    
    public Collection<Book> findBooks(SearchPattern pattern) {
        BookConverter converter = new BookConverter();
       
        @SuppressWarnings("unchecked")
        List<Vector> found = em.createNativeQuery(getSQLStatement(pattern)).getResultList();
        List<Book> retval = new ArrayList<Book>(found.size());
        for (Vector columns : found) {
            Book bookBean = new Book();
            long id = (Long)columns.get(0);
            Integer pubYear = (Integer)columns.get(1);
            String author = (String)columns.get(2);
            String title = (String)columns.get(3);
            String publisher = (String)columns.get(4);
            int avail = (Integer)columns.get(5);
            bookBean.setBookId((int)id);
            bookBean.setAuthor(author);
            bookBean.setTitle(title);
            bookBean.setPublisher(publisher);
            bookBean.setPublishYear(pubYear);
            bookBean.setHasAvailableCopies(avail > 0);
            retval.add(bookBean);
        }
        
        return retval;
    }
    
    public Collection<ReservationDetails> getBookCopies(long bookId) {
        ArrayList<ReservationDetails> retval = new ArrayList<ReservationDetails>();
        
        BookEntity bookEntity = em.find(BookEntity.class, bookId);
        
        if (bookEntity == null)
            return retval;
        
        Book book = new BookConverter().fromEntity(bookEntity);
        String queryTxt = "SELECT Id,returnDate,Copystate, (SELECT COUNT(*) FROM " +
                "Reservations R WHERE R.copy_Id=BC.Id) reservQueue " +
                "FROM BookCopies BC WHERE BC.book_Id="+Long.toString(bookId);
        @SuppressWarnings("unchecked")
        List<Vector> found = em.createNativeQuery(queryTxt).getResultList();
        retval.ensureCapacity(found.size());
        
        for(Vector columns : found) {
            long copyId = (Long)columns.get(0);
            Date returnDate = (Date)columns.get(1);
            int state = (Integer)columns.get(2);
            int reservQueue = (Integer)columns.get(3);
            
            BookCopy copy = new BookCopy(state, (int)copyId);
            copy.setBook(book);
            if (returnDate != null) {
                Calendar cal = new GregorianCalendar();
                cal.setTime(returnDate);
                copy.setReturnDate(cal);
            }
            
            ReservationDetails rd = new ReservationDetails();
            rd.setBookCopy(copy);
            rd.setQueueSize(reservQueue);
            
            retval.add(rd);
        }
        
        return retval;
    }
    
    public Collection<BookCopy> getBooksBorrowedBy(int readerId) {
        @SuppressWarnings("unchecked")
        List<Object[]> found = em.createNamedQuery("BookCopyEntity.GetAllBorrowedBy").setParameter(1,readerId).getResultList();
        ArrayList<BookCopy> retval = new ArrayList<BookCopy>(found.size());
        for(Object[] pair : found) {
            Book book = new BookConverter().fromEntity((BookEntity)pair[0]);
            BookCopy copy = new BookCopyConverter().fromEntity((BookCopyEntity)pair[1]);
            copy.setBook(book);
            retval.add(copy);
        }
        return retval;
    }
    
    public Collection<BookCopy> getBooksRequestedBy(int readerId) {
        @SuppressWarnings("unchecked")
        List<Object[]> found = em.createNamedQuery("BookCopyEntity.GetAllRequestedBy").setParameter(1,readerId).getResultList();
        ArrayList<BookCopy> retval = new ArrayList<BookCopy>(found.size());
        for(Object[] pair : found) {
            Book book = new BookConverter().fromEntity((BookEntity)pair[0]);
            BookCopy copy = new BookCopyConverter().fromEntity((BookCopyEntity)pair[1]);
            copy.setBook(book);
            retval.add(copy);
        }
        return retval;
    }
    
    public Collection<ReservationDetails> getBooksReservedBy(int readerId) {
        String queryTxt = "SELECT B.Id,Author,Title,Publisher,PublishYear," +
                "BC.Id,returnDate,Copystate, (SELECT COUNT(*) FROM " +
                "Reservations R WHERE R.copy_Id=BC.Id) reservQueue, R.Id " +
                "FROM Books B, BookCopies BC, Reservations R " +
                "WHERE BC.book_Id=B.Id AND R.copy_id=BC.Id AND R.reader_id=?1";
        @SuppressWarnings("unchecked")
        List<Vector> found = em.createNativeQuery(queryTxt).setParameter(1,readerId).getResultList();
        ArrayList<ReservationDetails> retval = new ArrayList<ReservationDetails>(found.size());
        
        for(Vector columns : found) {
            long bookId = (Long)columns.get(0);
            String author = (String)columns.get(1);
            String title = (String)columns.get(2);
            String publisher = (String)columns.get(3);
            int publishYear = (Integer)columns.get(4);
            long copyId = (Long)columns.get(5);
            Date returnDate = (Date)columns.get(6);
            int state = (Integer)columns.get(7);
            int reservQueue = (Integer)columns.get(8);
            int reservId = ((Long)columns.get(9)).intValue();
            
            Book book = new Book(title,author,publisher,publishYear,(int)bookId);
            
            BookCopy copy = new BookCopy(state, (int)copyId);
            copy.setBook(book);
            if (returnDate != null) {
                Calendar cal = new GregorianCalendar();
                cal.setTime(returnDate);
                copy.setReturnDate(cal);
            }
            
            ReservationDetails rd = new ReservationDetails();
            rd.setBookCopy(copy);
            rd.setQueueSize(reservQueue);
            rd.setYouInQueue(queuePlaceCounterBean.countPlace(copyId, reservId));
            
            retval.add(rd);
        }
        return retval;
    }
    
    
    /**
     * returns LIKE statement for single word.
     * Example: ("title","Algorithms") will produce "title LIKE '%Algorithms%'"
     * when ignoreFirstCase is FALSE and "(title LIKE '%Algorithms%' OR title
     * LIKE '%algorithms%')" when set to TRUE.
     */
    protected String getLikeStatement(String fieldName, String word, boolean ignoreFirstCase) {
        String retval = fieldName + " LIKE '%" + word + "%'";
        if (!ignoreFirstCase)
            return retval;
        String changedCaseWord;
        if (Character.isUpperCase(word.charAt(0)))
            changedCaseWord = Character.toLowerCase(word.charAt(0)) + word.substring(1);
        else
            changedCaseWord = Character.toUpperCase(word.charAt(0)) + word.substring(1);
        return "(" + retval + " OR " + fieldName + " LIKE '%" + changedCaseWord + "%')";
    }
    
    /** returns WHERE substatement for book certain field.
     * Splits fields to words and searches for these words.
     * Statement for arguments ("author","Stefan Grabinski") is:
     * (author LIKE '%Stefan%' AND author LIKE '%Grabinski%')
     * @param ignoreFirstCase whether to ignore size of first letter
     * (will find "Algorithm" and "algorithm").
     * @return statement or empty string if fieldValue does not contain anything
     */
    protected String getWildcardStatement(String fieldName, String fieldValue, boolean ignoreFirstCase) {
        String parts[] = fieldValue.split("\\s+");
        if (parts.length == 0)
            return "";
        
        Enumerator retval = new Enumerator(" AND ");
        for(int i=0; i<parts.length; ++i)
            retval.addNotQuote(getLikeStatement(fieldName, parts[i], ignoreFirstCase));
        return "(" + retval.toString() + ")";
    }
    
    /** returns full SQL search statement */
    protected String getSQLStatement(SearchPattern pattern) {
        Enumerator fields = new Enumerator(",",false);
        String availStatement = "(SELECT COUNT(*) FROM BookCopies BC WHERE " +
                "BC.book_Id=B.Id AND CopyState="+State.AVAIL.ordinal()+")";
        fields.add("Id").add("publishYear").add("author").add("title");
        fields.add("publisher").add(availStatement+" available");
        
        StringBuffer retval = new StringBuffer("SELECT ");
        retval.append(fields.toString()).append(" FROM Books B");
        
        Enumerator where = new Enumerator(" AND ");
        if(pattern.hasAuthor()) where.addNotQuote(getWildcardStatement("author", pattern.getAuthor(),false));
        if(pattern.hasTitle()) where.addNotQuote(getWildcardStatement("title", pattern.getTitle(),true));
        if(pattern.hasPublishYear()) where.add("publishYear",pattern.getPublishYear());
        if(pattern.hasPublisher()) where.add("publisher",pattern.getPublisher());
        if(pattern.getOnlyAvailableCopies()) where.addNotQuote(availStatement+" > 0");
        
        if (!where.isEmpty())
            retval.append(" WHERE ").append(where.toString());
        
        return retval.toString();
    }
    
}
