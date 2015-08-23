/*
 * BooksBean.java
 *
 * Created on 19 maj 2007, 00:37
 */

package edu.uj.elms.logic;

import edu.uj.elms.beans.Book;
import edu.uj.elms.beans.BookCopy;
import edu.uj.elms.converters.BookConverter;
import edu.uj.elms.converters.BookCopyConverter;
import edu.uj.elms.entities.BookCopyEntity;
import edu.uj.elms.entities.BookEntity;
import edu.uj.elms.exceptions.LibraryException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Piotrek
 */
@Stateless
public class BooksBean implements BooksLocal {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of BooksBean */
    public BooksBean() {
    }
    
    public void addBook(Book book, BookCopy copy) throws LibraryException {
        BookConverter bookConv = new BookConverter();
        BookCopyConverter copyConv = new BookCopyConverter();
        BookEntity bookEntity = bookConv.fromBean(book);
        BookCopyEntity bookCopyEntity = copyConv.fromBean(copy);
        bookCopyEntity.setBorrowedBy(null);
        bookCopyEntity.setBook(bookEntity);
        em.persist(bookEntity);
        em.persist(bookCopyEntity);
    }
    
    public void addBookCopy(int bookId, BookCopy copy) throws LibraryException {
        BookCopyConverter conv = new BookCopyConverter();
        BookCopyEntity newCopy = conv.fromBean(copy);
        newCopy.setBook(em.find(BookEntity.class, (long)bookId));
        em.persist(newCopy);
        // TODO test
    }
    
    public void deleteBook(int bookId) throws LibraryException {
        BookEntity book = em.find(BookEntity.class, (long)bookId);
        em.createNamedQuery("ReservationEntity.DeleteAllBookReserv").setParameter("bookId", book.getId()).executeUpdate();
        em.createNamedQuery("BookCopyEntity.DeleteAllBookCopies").setParameter("bookId", book.getId()).executeUpdate();
        em.remove(book);
        // TODO error handling?
    }
    
    public void deleteBookCopy(int copyId) throws LibraryException {
        BookCopyEntity copy = em.find(BookCopyEntity.class, (long)copyId);
        em.remove(copy);
        // TODO error handling?
    }
    
    
}
