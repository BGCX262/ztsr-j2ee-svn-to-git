/*
 * LibrarianSessionBean.java
 *
 * Created on 18 maj 2007, 16:05
 */

package edu.uj.elms.session;

import edu.uj.elms.beans.Book;
import edu.uj.elms.beans.BookCopy;
import edu.uj.elms.beans.LoginData;
import edu.uj.elms.beans.Reader;
import edu.uj.elms.exceptions.LibraryException;
import edu.uj.elms.logic.BookActionsLocal;
import edu.uj.elms.logic.BookFinderLocal;
import edu.uj.elms.logic.BooksLocal;
import edu.uj.elms.logic.UsersLocal;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Piotrek
 */
@Stateful(mappedName="LibrarianSession")
public class LibrarianSessionBean implements LibrarianSessionRemote, LibrarianSessionLocal {

    @EJB
    private BookFinderLocal bookFinderBean;

    @EJB
    private BooksLocal booksBean;

    @EJB
    private BookActionsLocal bookActionsBean;

    @EJB
    private UsersLocal usersBean;
    
    /** Creates a new instance of LibrarianSessionBean */
    public LibrarianSessionBean() {
    }
    
    public void addReader(Reader reader) throws LibraryException {
        usersBean.addReader(reader);
    }
    
    public void deleteUser(LoginData ld) throws LibraryException {
        usersBean.deleteUser(ld.getLogin());
    }
    
    public void addBook(Book book, BookCopy copy) throws LibraryException {
        booksBean.addBook(book, copy);
    }
    
    public void addBookCopy(int bookId, BookCopy copy) throws LibraryException {
        booksBean.addBookCopy(bookId, copy);
    }
    
    public void deleteBook(int bookId) throws LibraryException {
        booksBean.deleteBook(bookId);
    }
    
    public void deleteBookCopy(int copyId) throws LibraryException {
        booksBean.deleteBookCopy(copyId);
    }
    
    public void borrowAllRequestedBooks(int readerId) throws LibraryException {
        Collection<BookCopy> requested = bookFinderBean.getBooksRequestedBy(readerId);
        int[] copyIds = new int[requested.size()];
        
        int i=0;
        for(BookCopy bc : requested) {
            copyIds[i] = bc.getCopyId();
            ++i;
        }
        
        bookActionsBean.borrow(readerId, copyIds);
    }
    
    public Collection<Reader> getAllUsers() {
        return usersBean.getAllUsers();
    }
}
