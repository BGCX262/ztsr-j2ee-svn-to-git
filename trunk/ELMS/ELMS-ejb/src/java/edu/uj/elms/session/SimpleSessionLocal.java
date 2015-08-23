
package edu.uj.elms.session;

import edu.uj.elms.beans.SearchPattern;
import javax.ejb.Local;


/**
 * This is the business interface for SimpleSession enterprise bean.
 */
@Local
public interface SimpleSessionLocal {
    java.util.Collection<edu.uj.elms.beans.Book> findBooks(SearchPattern pattern);

    java.util.Collection<edu.uj.elms.beans.ReservationDetails> getBookCopies(long bookId);
    
}
