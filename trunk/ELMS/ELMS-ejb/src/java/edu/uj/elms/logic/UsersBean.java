/*
 * UsersBean.java
 *
 * Created on 18 maj 2007, 15:45
 */

package edu.uj.elms.logic;

import edu.uj.elms.beans.LoginData;
import edu.uj.elms.beans.Reader;
import edu.uj.elms.converters.ReaderConverter;
import edu.uj.elms.entities.ReaderEntity;
import edu.uj.elms.exceptions.LibraryException;
import edu.uj.elms.exceptions.LoginException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Piotrek
 */
@Stateless
public class UsersBean implements UsersLocal {

    @PersistenceContext
    private EntityManager em;
    
    
    
    /** Creates a new instance of UsersBean */
    public UsersBean() {
    }
    
    public void tryLogin(LoginData ld) throws LibraryException {
        ReaderEntity reader = em.find(ReaderEntity.class, (long)ld.getLogin());
        if (reader == null) {
            throw new LoginException(ResourceBundle.getBundle("edu/uj/elms/messages").getString("no_such_user"));
        }
        if (!reader.getPassword().equals(ld.getPassword())) {
            throw new LoginException(ResourceBundle.getBundle("edu/uj/elms/messages").getString("wrong_password"));
        }
    }
    
    public void addReader(Reader reader) {
        ReaderConverter conv = new ReaderConverter();
        em.persist(conv.fromBean(reader));
    }
    
    public void deleteUser(int id) throws LibraryException {
        ReaderEntity reader = em.find(ReaderEntity.class, (long)id);
        if (reader == null) {
            throw new LoginException(ResourceBundle.getBundle("edu/uj/elms/messages").getString("no_such_user"));
        }
        em.remove(reader);
    }
    
    public Collection<Reader> getAllUsers() {
        @SuppressWarnings("unchecked")
        List<ReaderEntity> found = em.createNamedQuery("ReaderEntity.selectAll").getResultList();
        Collection<Reader> retval = new ArrayList<Reader>(found.size());
        ReaderConverter conv = new ReaderConverter();
        for (ReaderEntity reader : found) {
            retval.add(conv.fromEntity(reader));
        }
        return retval;
    }
    
}
