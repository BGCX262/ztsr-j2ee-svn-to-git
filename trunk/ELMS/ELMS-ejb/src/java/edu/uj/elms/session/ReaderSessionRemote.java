
package edu.uj.elms.session;

import edu.uj.elms.beans.LoginData;
import edu.uj.elms.exceptions.LibraryException;
import javax.ejb.Remote;


/**
 * This is the business interface for ReaderSession enterprise bean.
 */
@Remote
public interface ReaderSessionRemote {
    void logIn(LoginData ld) throws LibraryException;

    void logOut();

    java.util.Collection<edu.uj.elms.beans.BookCopy> getRequestedBooks() throws LibraryException;

    java.util.Collection<edu.uj.elms.beans.BookCopy> getBorrowedBooks() throws LibraryException;

    java.util.Collection<edu.uj.elms.beans.ReservationDetails> getReservedBooks() throws LibraryException;

    void requestBooks(int[] copyIds) throws LibraryException;

    void reserveBooks(int[] copyIds) throws LibraryException;

    void cancelReservations(int[] copyIds) throws LibraryException;
    
}
