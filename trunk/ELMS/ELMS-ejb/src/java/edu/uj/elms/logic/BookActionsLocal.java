
package edu.uj.elms.logic;

import edu.uj.elms.exceptions.LibraryException;
import javax.ejb.Local;


/**
 * This is the business interface for BookActions enterprise bean.
 */
@Local
public interface BookActionsLocal {
    void reserve(int readerId, int[] copyIds) throws LibraryException;

    void cancelReservations(int readerId, int[] copyIds) throws LibraryException;

    void request(int readerId, int[] copyIds) throws LibraryException;

    void borrow(int readerId, int[] copyIds) throws LibraryException;
    
}
