/*
 * QueuePlaceCounterBean.java
 *
 * Created on 19 maj 2007, 00:48
 */

package edu.uj.elms.logic;

import edu.uj.elms.entities.ReservationEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * finds place of reader in reservation queue for a book copy
 *
 * @author Piotrek
 */
@Stateless
public class QueuePlaceCounterBean implements QueuePlaceCounterLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    public int countPlace(long copyId, int readerReservationId) {
        QueuePlaceCounter counter = new QueuePlaceCounter(readerReservationId);
        
        @SuppressWarnings("unchecked")
        List<ReservationEntity> found = em.createNamedQuery("ReservationEntity.GetReservForCopy").setParameter(1,copyId).getResultList();
        for(ReservationEntity reservation : found) {
            int id=reservation.getId().intValue();
            int link= (reservation.getPrevReserv() == null) ? -1 : reservation.getPrevReserv().getId().intValue();
            counter.handlePair(id,link);
        }
        
        return counter.getPlace();
    }
}
