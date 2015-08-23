
package edu.uj.elms.logic;

import edu.uj.elms.beans.Book;
import edu.uj.elms.beans.BookCopy;
import edu.uj.elms.exceptions.LibraryException;
import javax.ejb.Local;


/**
 * This is the business interface for Books enterprise bean.
 */
@Local
public interface BooksLocal {
    void addBook(Book book, BookCopy copy) throws LibraryException;

    void addBookCopy(int bookId, BookCopy copy) throws LibraryException;

    void deleteBook(int bookId) throws LibraryException;

    void deleteBookCopy(int copyId) throws LibraryException;
    
}
