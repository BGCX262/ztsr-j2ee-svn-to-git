package reader;

import edu.uj.elms.session.ReaderSessionRemote;
import edu.uj.elms.session.SimpleSessionRemote;

/**
 * @author Karolina
 */
public class SessionData {
    
    private String login;
    private ReaderSessionRemote library;
    private SimpleSessionRemote session;
    
    /** Creates a new instance of SessionData */
    public SessionData() {
        login = "";
        library = null;
        session = null;
    }
    
    public boolean isAnonymous() { return (login.equalsIgnoreCase("0")); }
    
    public void setLogin(String l) { login = l; }
    
    public String getLogin() { return login; }
    
    public void setLibrary(ReaderSessionRemote l) { library = l; }
    
    public ReaderSessionRemote getLibrary() { return library; }
   
    public void setSession(SimpleSessionRemote s) { session = s; }
    
    public SimpleSessionRemote getSession() { return session; }
   
}
