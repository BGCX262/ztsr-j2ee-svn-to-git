/*
 * SimpleSessionBean.java
 *
 * Created on 18 maj 2007, 16:10
 */

package edu.uj.elms.session;

import edu.uj.elms.beans.Book;
import edu.uj.elms.beans.ReservationDetails;
import edu.uj.elms.beans.SearchPattern;
import edu.uj.elms.logic.BookFinderLocal;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Session which does not require logging in.
 * @author Piotrek
 */
@WebService
@Stateless(mappedName="SimpleSession")
public class SimpleSessionBean implements SimpleSessionRemote, SimpleSessionLocal {

    @EJB
    private BookFinderLocal bookFinderBean;
    
    /** Creates a new instance of SimpleSessionBean */
    public SimpleSessionBean() {
    }
    
    @WebMethod
    public Collection<Book> findBooks(SearchPattern pattern) {
        return bookFinderBean.findBooks(pattern);
    }
    
    @WebMethod
    public Collection<ReservationDetails> getBookCopies(long bookId) {
        return bookFinderBean.getBookCopies(bookId);
    }
    
}
