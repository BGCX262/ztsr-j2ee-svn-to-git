
package edu.uj.elms.logic;

import edu.uj.elms.beans.LoginData;
import edu.uj.elms.beans.Reader;
import edu.uj.elms.exceptions.LibraryException;
import javax.ejb.Local;


/**
 * This is the business interface for Users enterprise bean.
 */
@Local
public interface UsersLocal {
    
    /**
     * tries to log in.
     * @throws LoginException when invalid password
     * @throws LogicException when user does not exist
     */
    void tryLogin(LoginData ld) throws LibraryException;

    void addReader(Reader reader);

    java.util.Collection<edu.uj.elms.beans.Reader> getAllUsers();

    void deleteUser(int id) throws LibraryException;
    
}
