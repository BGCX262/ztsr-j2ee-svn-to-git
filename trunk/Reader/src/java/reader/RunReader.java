/*
 * RunReader.java
 *
 * Created on 27 maj 2007, 22:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package reader;

import edu.uj.elms.session.SimpleSessionRemote;
import javax.naming.InitialContext;

/**
 *
 * @author Karolina
 */
public class RunReader {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            InitialContext ctx = new InitialContext();
            SimpleSessionRemote session = (SimpleSessionRemote) ctx.lookup("SimpleSession");
            MainForm mf = new MainForm(session);
            mf.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
