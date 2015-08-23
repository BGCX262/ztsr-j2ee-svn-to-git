/*
 * BookCopyEntity.java
 *
 * Created on 22 maj 2007, 12:39
 */

package edu.uj.elms.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class BookCopyEntity
 *
 *
 * @author Piotrek
 */
@Entity
@Table(name="BOOKCOPIES")
@NamedQueries({
    @NamedQuery(name="BookCopyEntity.GetAllBorrowedBy",
    query="SELECT b,bc FROM BookCopyEntity bc JOIN bc.book b " +
            "WHERE bc.borrowedBy.id= ?1 AND bc.copyState=edu.uj.elms.entities.BookCopyEntity.State.BORROWED"),
            @NamedQuery(name="BookCopyEntity.GetAllRequestedBy",
    query="SELECT b,bc FROM BookCopyEntity bc JOIN bc.book b " +
            "WHERE bc.borrowedBy.id= ?1 AND bc.copyState=edu.uj.elms.entities.BookCopyEntity.State.REQUESTED"),
            @NamedQuery(name="BookCopyEntity.ReaderRequestedThisCopy",
    query="SELECT COUNT(bc) FROM BookCopyEntity bc " +
            "WHERE bc.id=:copyId AND bc.borrowedBy.id=:readerId AND bc.copyState=edu.uj.elms.entities.BookCopyEntity.State.REQUESTED"),
            @NamedQuery(name="BookCopyEntity.ReaderBorrowedThisCopy",
    query="SELECT COUNT(bc) FROM BookCopyEntity bc " +
            "WHERE bc.id=:copyId AND bc.borrowedBy.id=:readerId AND bc.copyState=edu.uj.elms.entities.BookCopyEntity.State.BORROWED"),
            @NamedQuery(name="BookCopyEntity.DeleteAllBookCopies",
    query="DELETE FROM BookCopyEntity bc WHERE bc.book.id = :bookId")
})

public class BookCopyEntity implements Serializable {
    
    public enum State{AVAIL,BORROWED,UNAVAIL,REQUESTED};
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private State copyState;
    
    @Temporal(TemporalType.DATE)
    private
            Date returnDate;
    
    @ManyToOne(optional=false)
    private
            BookEntity book;
    
    @ManyToOne(optional=true)
    @JoinColumn(name="borrowed_by")
    private
            ReaderEntity borrowedBy;
    
    /**
     * Creates a new instance of BookCopyEntity
     */
    public BookCopyEntity() {
    }
    
    /**
     * Gets the id of this BookCopyEntity.
     *
     * @return the id
     */
    public Long getId() {
        return this.id;
    }
    
    /**
     * Sets the id of this BookCopyEntity to the specified value.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Returns a hash code value for the object.  This implementation computes
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    /**
     * Determines whether another object is equal to this BookCopyEntity.  The result is
     * <code>true</code> if and only if the argument is not null and is a BookCopyEntity object that
     * has the same id field values as this object.
     *
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookCopyEntity)) {
            return false;
        }
        BookCopyEntity other = (BookCopyEntity)object;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) return false;
        return true;
    }
    
    /**
     * Returns a string representation of the object.  This implementation constructs
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "edu.uj.elms.entities.BookCopy[id=" + id + "]";
    }
    
    public State getCopyState() {
        return copyState;
    }
    
    public void setCopyState(State copyState) {
        this.copyState = copyState;
    }
    
    public Date getReturnDate() {
        return returnDate;
    }
    
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    
    public BookEntity getBook() {
        return book;
    }
    
    public void setBook(BookEntity book) {
        this.book = book;
    }
    
    public ReaderEntity getBorrowedBy() {
        return borrowedBy;
    }
    
    public void setBorrowedBy(ReaderEntity borrowedBy) {
        this.borrowedBy = borrowedBy;
    }
    
    private static final long serialVersionUID = 425117185527691826L;
    
}
