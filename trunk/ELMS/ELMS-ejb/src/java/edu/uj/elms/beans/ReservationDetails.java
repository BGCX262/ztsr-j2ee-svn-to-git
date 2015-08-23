/*
 * ReservationDetails.java
 *
 * Created on 16 marzec 2007, 12:54
 */

package edu.uj.elms.beans;

import java.io.Serializable;

/**
 * 
 * @author Piotrek
 */
public class ReservationDetails implements Serializable {
    
    private static final int NOT_RESERVED = -1;
    
    /** number of people reserving this book */
    private int queueSize;
    
    /** when reader has reserved the book, this field will give his or her
     * position in queue (from 1 up).
     * Check {@link #youHaveReserved()} before reading this!
     * Value of this field when youHaveReserved() returns false is undefined.
     */
    private int youInQueue;
    
    private BookCopy bookCopy;

    public ReservationDetails(int queueSize) {
        this.setQueueSize(queueSize);
        setYouInQueue(NOT_RESERVED);
    }
    
    public ReservationDetails() {
        this(-1);
    }
    
    public boolean youHaveReserved() {
        return getYouInQueue() != NOT_RESERVED;
    }
    
    private static final long serialVersionUID = 5439294359408964797L;

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public int getYouInQueue() {
        return youInQueue;
    }

    public void setYouInQueue(int youInQueue) {
        this.youInQueue = youInQueue;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public String toString() {
        String retval = bookCopy.toString();
        retval += String.format(" queue: %d; you: %d", queueSize, youInQueue);
        return retval;
    }
    

}
