/*
 * Main.java
 *
 * Created on 27 maj 2007, 22:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package librarian;

import edu.uj.elms.session.LibrarianSessionRemote;
import edu.uj.elms.session.SimpleSessionRemote;
import javax.naming.InitialContext;

/**
 *
 * @author Karolina
 */
public class RunLibrarian {
    
    /** Creates a new instance of Main */
    public RunLibrarian() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          try{
            InitialContext ctx = new InitialContext();
            LibrarianSessionRemote library = (LibrarianSessionRemote) ctx.lookup("LibrarianSession"); 
            SimpleSessionRemote session = (SimpleSessionRemote) ctx.lookup("SimpleSession");
            AdminWindow mf = new AdminWindow(library, session);
            mf.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
