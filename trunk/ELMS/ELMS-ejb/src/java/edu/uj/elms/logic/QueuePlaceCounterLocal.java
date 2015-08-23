
package edu.uj.elms.logic;

import java.util.Collection;
import javax.ejb.Local;


/**
 * finds place of reader in reservation queue for a book copy
 */
@Local
public interface QueuePlaceCounterLocal {
    int countPlace(long copyId, int readerReservationId);
    
    
}
