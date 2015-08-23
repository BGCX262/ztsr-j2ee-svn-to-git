/*
 * BookCopy.java
 *
 * Created on 16 marzec 2007, 12:29
 */

package edu.uj.elms.beans;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Piotrek
 */
public class BookCopy implements Serializable {
    
    /** state of the book copy.
     * AVAIL - book available, BORROWED - book is borrowed,
     * UNAVAIL - book cannot be borrowed (eg. is repaired),
     * REQUESTED - book was requested
     */
    public enum State {AVAIL,BORROWED,UNAVAIL,REQUESTED};
    
    private int copyId;
    
    /** date of book return, <b>may be null</b> if the book has not been borrowed */
    private Calendar returnDate;
    
    private State state;
    
    private Book book;
    
    /** Creates a new instance of BookCopy */
    public BookCopy(int state, int copyId, Calendar returnDate) {
        try {
            this.setState(State.values()[state]);
        } catch(Exception e) {
            e.printStackTrace();
            this.setState(State.UNAVAIL);
        }
        this.setCopyId(copyId);
        this.setReturnDate(returnDate);
    }
    
    public BookCopy(int state, int copyId) {
        this(state, copyId,null);
    }
    
    public BookCopy(State state) {
        this.setState(state);
    }
    
    public BookCopy(int copyId) {
        this(State.AVAIL.ordinal(),copyId,null);
    }
    
    public BookCopy() {
        this(State.AVAIL);
    }
    
    private static final long serialVersionUID = 1672962649873290654L;

    public int getCopyId() {
        return copyId;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
    }

    public Calendar getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Calendar returnDate) {
        this.returnDate = returnDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String toString() {
        return String.format("copy %d of '%s' %s", getCopyId(), book.getTitle(), state.toString());
    }

    
}
