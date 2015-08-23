
package edu.uj.elms.logic;

import edu.uj.elms.beans.SearchPattern;
import javax.ejb.Local;


/**
 * This is the business interface for BookFinder enterprise bean.
 */
@Local
public interface BookFinderLocal {
    java.util.Collection<edu.uj.elms.beans.Book> findBooks(SearchPattern pattern);

    java.util.Collection<edu.uj.elms.beans.ReservationDetails> getBookCopies(long bookId);

    java.util.Collection<edu.uj.elms.beans.BookCopy> getBooksBorrowedBy(int readerId);

    java.util.Collection<edu.uj.elms.beans.BookCopy> getBooksRequestedBy(int readerId);

    java.util.Collection<edu.uj.elms.beans.ReservationDetails> getBooksReservedBy(int readerId);
    
}
