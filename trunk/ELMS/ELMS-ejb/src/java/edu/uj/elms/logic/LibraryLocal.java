
package edu.uj.elms.logic;

import javax.ejb.Local;


/**
 * This is the business interface for Library enterprise bean.
 */
@Local
public interface LibraryLocal {
    /**
     * counts date when book borrowed in this moment should be returned.
     */
    java.util.Calendar countReturnDate();
    
}
