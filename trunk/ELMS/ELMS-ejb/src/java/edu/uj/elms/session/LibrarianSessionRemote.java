
package edu.uj.elms.session;

import edu.uj.elms.beans.Book;
import edu.uj.elms.beans.BookCopy;
import edu.uj.elms.beans.LoginData;
import edu.uj.elms.beans.Reader;
import edu.uj.elms.exceptions.LibraryException;
import javax.ejb.Remote;


/**
 * This is the business interface for LibrarianSession enterprise bean.
 */
@Remote
public interface LibrarianSessionRemote {
    void borrowAllRequestedBooks(int readerId) throws LibraryException;

    void addBook(Book book, BookCopy copy) throws LibraryException;

    void addReader(Reader reader) throws LibraryException;

    java.util.Collection<edu.uj.elms.beans.Reader> getAllUsers();
    
    void addBookCopy(int bookId, BookCopy copy) throws LibraryException;

    void deleteBookCopy(int copyId) throws LibraryException;

    void deleteBook(int bookId) throws LibraryException;

    void deleteUser(LoginData ld) throws LibraryException;
}
