/*
 * ReaderSessionBean.java
 *
 * Created on 18 maj 2007, 15:05
 */

package edu.uj.elms.session;

import edu.uj.elms.beans.BookCopy;
import edu.uj.elms.beans.LoginData;
import edu.uj.elms.beans.ReservationDetails;
import edu.uj.elms.exceptions.LibraryException;
import edu.uj.elms.exceptions.PermissionException;
import edu.uj.elms.logic.BookActionsLocal;
import edu.uj.elms.logic.BookFinderLocal;
import edu.uj.elms.logic.UsersLocal;
import java.util.Collection;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Piotrek
 */
@Stateful(mappedName="ReaderSession")
public class ReaderSessionBean implements ReaderSessionRemote, ReaderSessionLocal {

    @EJB
    private BookActionsLocal bookActionsBean;

    @EJB
    private BookFinderLocal bookFinderBean;

    @EJB
    private UsersLocal usersBean;
    
    private boolean anonymous;
    
    private int readerId;
    
    /** Creates a new instance of ReaderSessionBean */
    public ReaderSessionBean() {
        anonymous = true;
    }
    
    public void logIn(LoginData ld) throws LibraryException {
        usersBean.tryLogin(ld);
        this.readerId = ld.getLogin();
        anonymous = false;
    }
    
    public void logOut() {
        //
    }
    
    public Collection<BookCopy> getRequestedBooks() throws LibraryException {
        loginRequired();
        return bookFinderBean.getBooksRequestedBy(readerId);
    }
    
    public Collection<BookCopy> getBorrowedBooks() throws LibraryException {
        loginRequired();
        return bookFinderBean.getBooksBorrowedBy(readerId);
    }
    
    public Collection<ReservationDetails> getReservedBooks() throws LibraryException {
        loginRequired();
        return bookFinderBean.getBooksReservedBy(readerId);
    }
    
    public void requestBooks(int[] copyIds) throws LibraryException {
        loginRequired();
        bookActionsBean.request(readerId, copyIds);
    }
    
    public void reserveBooks(int[] copyIds) throws LibraryException {
        loginRequired();
        bookActionsBean.reserve(readerId, copyIds);
    }
    
    public void cancelReservations(int[] copyIds) throws LibraryException {
        loginRequired();
        bookActionsBean.cancelReservations(readerId, copyIds);
    }
    
    private void loginRequired() throws PermissionException {
        if (anonymous)
            throw new PermissionException(ResourceBundle.getBundle("edu/uj/elms/messages").getString("you_need_to_log_in_to_do_this"));
    }
    
}
