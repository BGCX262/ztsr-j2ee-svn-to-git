/*
 * ReaderConverter.java
 *
 * Created on 26 maj 2007, 09:37
 */

package edu.uj.elms.converters;

import edu.uj.elms.beans.LoginData;
import edu.uj.elms.beans.Reader;
import edu.uj.elms.entities.ReaderEntity;

/**
 * Converts from {@link ReaderEntity} to {@link LoginData} and opposite.
 * @author Piotrek
 */
public class ReaderConverter {
    
    /** Creates a new instance of ReaderConverter */
    public ReaderConverter() {
    }
    
    public ReaderEntity fromBean(Reader bean) {
        ReaderEntity retval = new ReaderEntity();
        retval.setId((long)bean.getId());
        retval.setPassword(bean.getPassword());
        retval.setFullName(bean.getFullName());
        return retval;
    }
    
    public Reader fromEntity(ReaderEntity entity) {
        Reader retval = new Reader();
        retval.setId(entity.getId().intValue());
        retval.setPassword(entity.getPassword());
        retval.setFullName(entity.getFullName());
        return retval;
    }
    
}
