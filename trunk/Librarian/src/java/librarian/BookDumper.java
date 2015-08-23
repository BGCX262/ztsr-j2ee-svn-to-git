/*
 * BookDumper.java
 *
 * Created on 28 marzec 2007, 13:28
 */

package librarian;

import java.util.Collection;
import edu.uj.elms.beans.*;

/**
 * Creates printable summary of books.
 * @author Piotrek
 */
public class BookDumper {
    
    /** Creates a new instance of BookDumper.
     */
    public BookDumper() {
    }
    
    public String dump(Book book, boolean partOfCopy) {
        String retval="";
        if(!partOfCopy)
            retval += Integer.toString(book.getBookId())+" ";
        retval +=book.getAuthor()+" \""+book.getTitle()+"\" ";
        if(!partOfCopy) {
            if (book.getHasAvailableCopies())
                retval += "s¹ egz.";
            else
                retval += "nie ma egz.";
        }
        return retval;
    }
    
    public String dump(BookCopy copy) {
        StringBuffer retval = new StringBuffer(dump(copy.getBook(),true));
        retval.append(" copy id: ").append(copy.getCopyId()).append(" ");
        retval.append(copy.getState().toString());
        if(copy.getState() == BookCopy.State.BORROWED) {
            retval.append(" do oddania ");
            retval.append(copy.getReturnDate().get(java.util.Calendar.YEAR));
        }
        return retval.toString();
    }
    
    public String dump(ReservationDetails rd) {
        StringBuffer retval = new StringBuffer(dump(rd.getBookCopy()));
        if (rd.getBookCopy().getState() != BookCopy.State.BORROWED)
            return retval.toString();
        retval.append(" w kolejce: ").append(rd.getQueueSize());
        if (rd.youHaveReserved()) {
            retval.append(" twoje miejsce: ").append(rd.getYouInQueue());
        }
        return retval.toString();
    }
    
    public String dumpBooks(Collection<Book> books) {
        StringBuffer retval = new StringBuffer();
        for(Book book : books) {
            retval.append(dump(book,false)).append("\n");
        }
        return retval.toString();
    }
    
    public String dumpReservations(Collection<ReservationDetails> bookCopies) {
        StringBuffer retval = new StringBuffer();
        for(ReservationDetails copy : bookCopies) {
            retval.append(dump(copy)).append("\n");
        }
        return retval.toString();
    }
    
}
