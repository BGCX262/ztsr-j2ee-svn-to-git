/*
 * LibraryBean.java
 *
 * Created on 19 maj 2007, 00:10
 */

package edu.uj.elms.logic;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.ejb.Stateless;

/**
 *
 * @author Piotrek
 */
@Stateless
public class LibraryBean implements edu.uj.elms.logic.LibraryLocal {
    
    /** Creates a new instance of LibraryBean */
    public LibraryBean() {
    }
    
    /** counts date when book borrowed in this moment should be returned.
     */
    public Calendar countReturnDate() {
        Calendar retval = new GregorianCalendar(); // today
        BorrowTime bt = getBorrowTime();
        retval.add(Calendar.DAY_OF_YEAR, bt.getDays());
        retval.add(Calendar.MONTH, bt.getMonths());
        return retval;
    }
    
    private BorrowTime getBorrowTime() {
        return new BorrowTime(0,1); // TODO implement
    }
    
    public void setBorrowTime(BorrowTime bt) {
        // TODO implement
    }
    
}
