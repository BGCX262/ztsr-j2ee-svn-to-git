/*
 * BookActionsBean.java
 *
 * Created on 18 maj 2007, 16:44
 */

package edu.uj.elms.logic;

import edu.uj.elms.entities.BookCopyEntity;
import edu.uj.elms.entities.ReaderEntity;
import edu.uj.elms.entities.ReservationEntity;
import edu.uj.elms.exceptions.LibraryException;
import edu.uj.elms.exceptions.LogicException;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Piotrek
 */
@Stateless
public class BookActionsBean implements BookActionsLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private LibraryLocal libraryBean;
    
    /** Creates a new instance of BookActionsBean */
    public BookActionsBean() {
    }
    
    public void reserve(int readerId, int[] copyIds) throws LibraryException {
        for(int copyId : copyIds) {
            reserve((long)readerId, (long)copyId);
        }
    }
    
    public void cancelReservations(int readerId, int[] copyIds) throws LibraryException {
        for(int copyId : copyIds)
            cancelReservation(readerId, copyId);
    }
    
    public void request(int readerId, int[] copyIds) throws LibraryException {
        for(int copyId : copyIds)
            request((long)readerId, (long)copyId);
    }
    
    public void borrow(int readerId, int[] copyIds) throws LibraryException {
        Calendar returnDate = libraryBean.countReturnDate();
        for(int copyId : copyIds) {
            borrow((long)readerId, (long)copyId, returnDate);
        }
    }
    
    private void borrow(long readerId, long copyId, Calendar returnDate) throws LibraryException {
        checkBorrowOk(readerId, copyId);
        
        BookCopyEntity borrowed = em.find(BookCopyEntity.class, copyId);
        borrowed.setCopyState(BookCopyEntity.State.BORROWED);
        borrowed.setReturnDate(returnDate.getTime());
    }
    
    private void cancelReservation(int readerId, int copyId) throws LogicException {
        @SuppressWarnings("unchecked")
        List<ReservationEntity> found = em.createNamedQuery("ReservationEntity.GetSingleReserv"
                ).setParameter("copyId",copyId).setParameter("readerId",readerId).getResultList();
        if (found.size() != 1) {
            throw new LogicException(String.format(ResourceBundle.getBundle("edu/uj/elms/messages").getString(
                    "no_reservation_for_book_copy"), copyId));
        }
        ReservationEntity cancelled = found.get(0);
        
        @SuppressWarnings("unchecked")
        List<ReservationEntity> foundNext = em.createNamedQuery("ReservationEntity.GetNextReserv"
                ).setParameter("reservId", cancelled.getId()).getResultList();
        if (foundNext.size() != 0) {
            ReservationEntity next = foundNext.get(0);
            next.setPrevReserv(cancelled.getPrevReserv());
            em.merge(next);
        }
        
        em.remove(cancelled);
    }
    
    private void reserve(long readerId, long copyId) throws LogicException {
        checkReservationOk(readerId, copyId);
        
        BookCopyEntity copy = (BookCopyEntity)em.find(BookCopyEntity.class, copyId);
        ReaderEntity reader = (ReaderEntity)em.find(ReaderEntity.class, readerId);
        ReservationEntity newReservation = new ReservationEntity();
        newReservation.setBookCopy(copy);
        newReservation.setReader(reader);
        
        @SuppressWarnings("unchecked")
        List<ReservationEntity> found = em.createNamedQuery("ReservationEntity.GetLastReserv"
                ).setParameter("copyId",copyId).getResultList();
        
        if (found.size() == 0) {
            newReservation.setPrevReserv(null);
        } else {
            newReservation.setPrevReserv(found.get(0));
        }
        
        em.persist(newReservation);
    }
    
    private void request(long readerId, long copyId) throws LogicException {
        checkRequestOk(readerId, copyId);
        
        BookCopyEntity copy = em.find(BookCopyEntity.class, copyId);
        copy.setBorrowedBy(em.find(ReaderEntity.class, readerId));
        copy.setCopyState(BookCopyEntity.State.REQUESTED);
    }
    
    
    private void checkReservationOk(long readerId, long copyId) throws LogicException {
        BookCopyEntity copy = em.find(BookCopyEntity.class, copyId);
        
        if (copy == null) {
            throw new LogicException(String.format(ResourceBundle.getBundle("edu/uj/elms/messages").getString(
                    "book_copy_no._%d_does_not_exist"), copyId));
        }
        if (copy.getCopyState() != BookCopyEntity.State.BORROWED && copy.getCopyState() != BookCopyEntity.State.REQUESTED) {
            throw new LogicException(String.format(ResourceBundle.getBundle("edu/uj/elms/messages").getString(
                    "book_copy_no._%d_cannot_be_reserved_because_it_is_not_borrowed"),copyId));
        }
        
        long reservCount = (Long)em.createNamedQuery("ReservationEntity.ReaderReservedThisCopy"
                ).setParameter("copyId", copyId).setParameter("readerId", readerId).getSingleResult();
        if (reservCount != 0) {
            throw new LogicException(ResourceBundle.getBundle("edu/uj/elms/messages").getString(
                    "you_have_already_reserved_this_book_copy"));
        }
        
        long reqCount = (Long)em.createNamedQuery("BookCopyEntity.ReaderRequestedThisCopy"
                ).setParameter("copyId", copyId).setParameter("readerId", readerId).getSingleResult();
        if (reqCount != 0) {
            throw new LogicException(ResourceBundle.getBundle("edu/uj/elms/messages").getString(
                    "you_cannot_reserve_book_copy_you_have_already_requested"));
        }
        
        long borrowCount = (Long)em.createNamedQuery("BookCopyEntity.ReaderBorrowedThisCopy"
                ).setParameter("copyId", copyId).setParameter("readerId", readerId).getSingleResult();
        if (borrowCount != 0) {
            throw new LogicException(ResourceBundle.getBundle("edu/uj/elms/messages").getString(
                    "you_cannot_reserve_book_copy_you_have_already_borrowed"));
        }
    }
    
    private void checkRequestOk(long readerId, long copyId) throws LogicException {
        BookCopyEntity copy = em.find(BookCopyEntity.class, copyId);
        if (copy == null) {
            throw new LogicException(String.format(ResourceBundle.getBundle("edu/uj/elms/messages").getString(
                    "book_copy_no._%d_does_not_exist"), copyId));
        }
        if (copy.getCopyState() != BookCopyEntity.State.AVAIL) {
            throw new LogicException(String.format(ResourceBundle.getBundle("edu/uj/elms/messages").getString(
                    "book_copy_no._%d_cannot_be_requested,_choose_available_copy"),copyId));
        }
        
        ReaderEntity reader = em.find(ReaderEntity.class, readerId);
        if (reader == null) {
            throw new LogicException(String.format(ResourceBundle.getBundle("edu/uj/elms/messages").getString(
                    "no_such_user"), copyId));
        }
    }
    
    private void checkBorrowOk(long readerId, long copyId) throws LogicException {
        BookCopyEntity borrowed = em.find(BookCopyEntity.class, copyId);
        
        if (borrowed == null) {
            throw new LogicException(String.format(ResourceBundle.getBundle("edu/uj/elms/messages").getString(
                    "book_copy_no._%d_does_not_exist"), copyId));
        }
        if (borrowed.getBorrowedBy() == null || borrowed.getBorrowedBy().getId() != readerId
                || borrowed.getCopyState() != BookCopyEntity.State.REQUESTED) {
            throw new LogicException(String.format(ResourceBundle.getBundle("edu/uj/elms/messages").getString(
                    "no_request_for_book_copy"), copyId));
        }
    }
    
}
