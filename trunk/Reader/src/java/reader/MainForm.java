package reader;

import edu.uj.elms.session.ReaderSessionRemote;
import edu.uj.elms.session.SimpleSessionRemote;
import javax.swing.JFrame;


/**
 * @author  Karolina
 */
public class MainForm extends javax.swing.JFrame {
    
    private SessionData sData;
    
    /** Creates new form LoginForm */
    public MainForm(SimpleSessionRemote session) {
        sData = new SessionData();
        sData.setSession(session);
        initComponents();
        this.setLocationRelativeTo(null);
        initPanels();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Library");
        setLocationByPlatform(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                logoutSession(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 693, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 504, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void logoutSession(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_logoutSession
        try{
            if (sData.getLibrary() != null)
                sData.getLibrary().logOut();
    }catch(Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_logoutSession
    
    private void initPanels(){
        LoginPanel loginPanel = new LoginPanel(sData);
        MenuPanel menuPanel = new MenuPanel(sData);
        StatePanel statePanel = new StatePanel(sData);
        FindPanel findPanel = new FindPanel(sData);
        ResultPanel resultPanel = new ResultPanel(sData);
        DetailsPanel detailsPanel = new DetailsPanel(sData);
        BorrowedPanel borrowedPanel = new BorrowedPanel(sData);
        RequestedPanel requestedPanel = new RequestedPanel(sData);
        ReservedPanel reservedPanel = new ReservedPanel(sData);
        
        loginPanel.setChildPanel(menuPanel);
        menuPanel.setParentPanel(loginPanel);
        menuPanel.setStatePanel(statePanel);
        menuPanel.setFindPanel(findPanel);
        statePanel.setParentPanel(menuPanel);
        statePanel.setBorrowedP(borrowedPanel);
        statePanel.setRequestedP(requestedPanel);
        statePanel.setReserverP(reservedPanel);
        findPanel.setParentPanel(menuPanel);
        findPanel.setResultPanel(resultPanel);
        resultPanel.setParentPanel(findPanel);
        resultPanel.setDetailsPanel(detailsPanel);
        detailsPanel.setParentPanel(resultPanel);
        borrowedPanel.setParentPanel(statePanel);
        requestedPanel.setParentPanel(statePanel);
        reservedPanel.setParentPanel(statePanel);
        
        this.setLayout(new java.awt.FlowLayout());
        this.add(loginPanel);
        this.add(menuPanel);
        this.add(statePanel);
        this.add(findPanel);
        this.add(resultPanel);
        this.add(detailsPanel);
        this.add(borrowedPanel);
        this.add(reservedPanel);
        this.add(requestedPanel);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}