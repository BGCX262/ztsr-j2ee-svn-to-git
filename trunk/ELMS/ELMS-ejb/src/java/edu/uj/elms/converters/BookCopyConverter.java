/*
 * BookCopyConverter.java
 *
 * Created on 22 maj 2007, 23:13
 */

package edu.uj.elms.converters;

import edu.uj.elms.beans.BookCopy;
import edu.uj.elms.entities.BookCopyEntity;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Piotrek
 */
public class BookCopyConverter {
    
    /** Creates a new instance of BookCopyConverter */
    public BookCopyConverter() {
    }
    
    private BookCopy.State toBeanState(BookCopyEntity.State entityState) {
        switch(entityState) {
            case AVAIL:
                return BookCopy.State.AVAIL;
            case BORROWED:
                return BookCopy.State.BORROWED;
            case REQUESTED:
                return BookCopy.State.REQUESTED;
            default:
                return BookCopy.State.UNAVAIL;
        }
    }
    
    private BookCopyEntity.State toEntityState(BookCopy.State beanState) {
        switch(beanState) {
            case AVAIL:
                return BookCopyEntity.State.AVAIL;
            case BORROWED:
                return BookCopyEntity.State.BORROWED;
            case REQUESTED:
                return BookCopyEntity.State.REQUESTED;
            default:
                return BookCopyEntity.State.UNAVAIL;
        }
    }
    
    public BookCopy fromEntity(BookCopyEntity entity) {
        BookCopy retval = new BookCopy();
        retval.setCopyId(entity.getId().intValue());
        retval.setState(toBeanState(entity.getCopyState()));
        if (entity.getReturnDate() != null) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(entity.getReturnDate());
            retval.setReturnDate(cal);
        }
        return retval;
    }
    
    public BookCopyEntity fromBean(BookCopy bean) {
        BookCopyEntity retval = new BookCopyEntity();
        retval.setCopyState(toEntityState(bean.getState()));
        retval.setId((long)bean.getCopyId());
        if (bean.getReturnDate() != null)
            retval.setReturnDate(bean.getReturnDate().getTime());
        return retval;
    }
    
}
