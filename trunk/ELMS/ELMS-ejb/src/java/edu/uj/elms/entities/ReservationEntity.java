/*
 * ReservationEntity.java
 *
 * Created on 25 maj 2007, 00:52
 */

package edu.uj.elms.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity class ReservationEntity
 *
 * @author Piotrek
 */
@Entity
@Table(name="RESERVATIONS")
@NamedQueries({
    @NamedQuery(name="ReservationEntity.GetReservForCopy",
    query="SELECT r FROM ReservationEntity r WHERE r.bookCopy.id=?1"),
    @NamedQuery(name="ReservationEntity.GetSingleReserv",
    query="SELECT r FROM ReservationEntity r WHERE r.bookCopy.id=:copyId AND r.reader.id=:readerId"),
            @NamedQuery(name="ReservationEntity.GetNextReserv",
    query="SELECT r FROM ReservationEntity r WHERE r.prevReserv.id=:reservId"),
            @NamedQuery(name="ReservationEntity.ReaderReservedThisCopy",
    query="SELECT COUNT(r) FROM ReservationEntity r WHERE r.bookCopy.id=:copyId AND r.reader.id=:readerId"),
            @NamedQuery(name="ReservationEntity.GetLastReserv",
    query="SELECT r FROM ReservationEntity r WHERE r.bookCopy.id=:copyId AND NOT EXISTS (SELECT r2 FROM ReservationEntity r2 WHERE r2.prevReserv=r)"),
            @NamedQuery(name="ReservationEntity.DeleteAllBookReserv",
    query="DELETE FROM ReservationEntity r WHERE r.bookCopy.book.id=:bookId")
})
public class ReservationEntity implements Serializable {
    
    
    private Long id;
    
    
    private ReservationEntity prevReserv;
    
    
    private
            ReaderEntity reader;
    
    
    private
            BookCopyEntity bookCopy;
    
    /** Creates a new instance of ReservationEntity */
    public ReservationEntity() {
    }
    
    /**
     * Gets the id of this ReservationEntity.
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }
    
    /**
     * Sets the id of this ReservationEntity to the specified value.
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
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }
    
    /**
     * Determines whether another object is equal to this ReservationEntity.  The result is
     * <code>true</code> if and only if the argument is not null and is a ReservationEntity object that
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservationEntity)) {
            return false;
        }
        ReservationEntity other = (ReservationEntity)object;
        if (this.getId() != other.getId() && (this.getId() == null || !this.getId().equals(other.getId()))) return false;
        return true;
    }
    
    /**
     * Returns a string representation of the object.  This implementation constructs
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "edu.uj.elms.entities.ReservationEntity[id=" + getId() + "]";
    }
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="prev_reserv")
    public ReservationEntity getPrevReserv() {
        return prevReserv;
    }
    
    public void setPrevReserv(ReservationEntity prevReserv) {
        this.prevReserv = prevReserv;
    }
    
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    public ReaderEntity getReader() {
        return reader;
    }
    
    public void setReader(ReaderEntity reader) {
        this.reader = reader;
    }
    
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    @JoinColumn(name="copy_id")
    public BookCopyEntity getBookCopy() {
        return bookCopy;
    }
    
    public void setBookCopy(BookCopyEntity bookCopy) {
        this.bookCopy = bookCopy;
    }
    
    private static final long serialVersionUID = -91884686589289291L;
    
}
