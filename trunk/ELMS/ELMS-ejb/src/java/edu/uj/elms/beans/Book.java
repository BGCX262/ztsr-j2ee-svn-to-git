package edu.uj.elms.beans;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 
 * @author Piotrek
 */
public class Book extends AbstractBook implements Serializable {

    private int bookId;
    
    private boolean hasAvailableCopies;

    public Book(String title, String author, String publisher, int publishYear, int id) {
        super(title, author, publisher, publishYear);
        setBookId(id);
        setHasAvailableCopies(false);
    }
    
    public Book(String title, String author, String publisher, int publishYear) {
        this(title, author, publisher, publishYear, 0);
    }
    
    public Book() {
        // nothing
    }
    
    private static final long serialVersionUID = 7236658448454561510L;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public boolean getHasAvailableCopies() {
        return hasAvailableCopies;
    }

    public void setHasAvailableCopies(boolean hasAvailableCopies) {
        this.hasAvailableCopies = hasAvailableCopies;
    }

    public String toString() {
        String retval = String.format("book '%s' by %s. %s, %d. id %d", getTitle(),
                getAuthor(), getPublisher(), getPublishYear(), getBookId());
        if (getHasAvailableCopies())
            retval += " A";
        else
            retval += " N/A";
        return retval;
    }
    

}
