package reader;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import edu.uj.elms.beans.ReservationDetails;

/**
 * @author  Karolina
 */
public class ReservedPanel extends javax.swing.JPanel {
    
    private SessionData sData;
    private StatePanel parentPanel;
    
    /** Creates new form ReservedPanel */
    public ReservedPanel(SessionData s) {
        initComponents();
        this.setVisible(false);
        sData = s;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        codePanel = new javax.swing.JPanel();
        numberLabel = new javax.swing.JLabel();
        tmpLabel = new javax.swing.JLabel();
        returnButton = new javax.swing.JButton();
        titlePanel = new javax.swing.JPanel();
        titileLabel = new javax.swing.JLabel();
        reservedLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reservedTable = new javax.swing.JTable();

        codePanel.setBackground(new java.awt.Color(0, 204, 204));
        numberLabel.setFont(new java.awt.Font("Tahoma", 2, 12));

        tmpLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        tmpLabel.setText(java.util.ResourceBundle.getBundle("reader/reader").getString("cart_number"));

        org.jdesktop.layout.GroupLayout codePanelLayout = new org.jdesktop.layout.GroupLayout(codePanel);
        codePanel.setLayout(codePanelLayout);
        codePanelLayout.setHorizontalGroup(
            codePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, codePanelLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .add(tmpLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 91, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(numberLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 213, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(64, 64, 64))
        );
        codePanelLayout.setVerticalGroup(
            codePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(codePanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(codePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tmpLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(numberLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        returnButton.setFont(new java.awt.Font("Tahoma", 1, 12));
        returnButton.setText(java.util.ResourceBundle.getBundle("reader/reader").getString("back"));
        returnButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                returnButtonKeyPressed(evt);
            }
        });
        returnButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returnButtonMouseClicked(evt);
            }
        });

        titileLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        titileLabel.setText(java.util.ResourceBundle.getBundle("reader/reader").getString("reserved_books"));

        reservedLabel.setFont(new java.awt.Font("Tahoma", 1, 12));

        cancelButton.setFont(new java.awt.Font("Tahoma", 1, 12));
        cancelButton.setText(java.util.ResourceBundle.getBundle("reader/reader").getString("cancel_reservations"));
        cancelButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cancelButtonKeyPressed(evt);
            }
        });
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelButtonMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout titlePanelLayout = new org.jdesktop.layout.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(titlePanelLayout.createSequentialGroup()
                .add(37, 37, 37)
                .add(titileLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(reservedLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 283, Short.MAX_VALUE)
                .add(cancelButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 159, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(titlePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(titileLabel)
                    .add(reservedLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(cancelButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        reservedTable.setFont(new java.awt.Font("Arial", 0, 12));
        reservedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                  ResourceBundle.getBundle("reader/reader").getString("author"), 
				ResourceBundle.getBundle("reader/reader").getString("title"),
				ResourceBundle.getBundle("reader/reader").getString("book_id"), 
				ResourceBundle.getBundle("reader/reader").getString("return_date"), 
				ResourceBundle.getBundle("reader/reader").getString("you_in_queue"),
				ResourceBundle.getBundle("reader/reader").getString("cancel")
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        reservedTable.setSelectionBackground(new java.awt.Color(0, 204, 204));
        jScrollPane1.setViewportView(reservedTable);

        org.jdesktop.layout.GroupLayout tablePanelLayout = new org.jdesktop.layout.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, tablePanelLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 628, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(25, 25, 25))
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, tablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(titlePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(29, 29, 29)
                        .add(codePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 63, Short.MAX_VALUE)
                        .add(returnButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(53, 53, 53))
            .add(layout.createSequentialGroup()
                .add(tablePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(codePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(returnButton))
                .add(20, 20, 20)
                .add(titlePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(24, 24, 24)
                .add(tablePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void returnButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_returnButtonKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            this.returnButtonAccept();
        }
    }//GEN-LAST:event_returnButtonKeyPressed
    
    private void cancelButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cancelButtonKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            this.cancelButtonAccept();
        }
    }//GEN-LAST:event_cancelButtonKeyPressed
    
    private void cancelButtonAccept(){
        int j = this.reservedTable.getRowCount();
        int[] bookIds = new int[j];
        int k = 0;
        for (int i = 0 ; i < j ; i++) {
            if(this.reservedTable.getModel().getValueAt(i,5).equals(true)){
                bookIds[k] = Integer.parseInt(this.reservedTable.getModel().getValueAt(i,2).toString());
                k++;
            }
        }
        
        if (k == 0){
            JOptionPane.showMessageDialog(this,
                    ResourceBundle.getBundle("reader/reader").getString("no_reservations_been_chosen_to_cancel"),
                    ResourceBundle.getBundle("reader/reader").getString("warning"),
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try{
            int[] bookToCancel = new int[k];
            for (int l = 0; l < k; l++){
                bookToCancel[l] = bookIds[l];
            }
            sData.getLibrary().cancelReservations(bookToCancel);
            JOptionPane.showMessageDialog(this,
                    ResourceBundle.getBundle("reader/reader").getString("chosen_reservations_been_cancelled"),
                    ResourceBundle.getBundle("reader/reader").getString("information"),
                    JOptionPane.INFORMATION_MESSAGE);
            for (int i = j -1 ; i >= 0 ; i--) {
                if(this.reservedTable.getModel().getValueAt(i,5).equals(true)){
                    ((DefaultTableModel)this.reservedTable.getModel()).removeRow(i);
                }
            }
            this.tablePanel.setVisible(this.reservedTable.getRowCount()>0);
            this.reservedLabel.setText(Integer.toString(this.reservedTable.getRowCount()));
            this.cancelButton.setVisible(this.reservedTable.getRowCount()>0);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage(),
                    ResourceBundle.getBundle("reader/reader").getString("warning"),
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseClicked
        this.cancelButtonAccept();
    }//GEN-LAST:event_cancelButtonMouseClicked
    
    private void returnButtonAccept(){
        this.setVisible(false);
        this.clearFields();
        parentPanel.setVisible(true);
    }
    
    private void returnButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnButtonMouseClicked
        this.returnButtonAccept();
    }//GEN-LAST:event_returnButtonMouseClicked
    
    
    public void displayResults(Collection<ReservationDetails> books){
        this.numberLabel.setText(sData.getLogin());
        this.reservedLabel.setText(Integer.toString(books.size()));
        this.tablePanel.setVisible(books.size()>0);
        this.cancelButton.setVisible(books.size()>0);
        for (ReservationDetails b : books) {
            Object[] obj = new Object[6];
            obj[0] = b.getBookCopy().getBook().getAuthor();
            obj[1] = b.getBookCopy().getBook().getTitle();
            obj[2] = b.getBookCopy().getCopyId();
            
            switch(b.getBookCopy().getState()) {
                case BORROWED:
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    obj[3] = df.format(b.getBookCopy().getReturnDate().getTime());
                    break;
                default: // REQUESTED
                    obj[3] = "-";
                    break;
            }
            
            obj[4] = b.getYouInQueue();
            obj[5] = false;
            ((DefaultTableModel)this.reservedTable.getModel()).addRow(obj);
        }
    }
    
    private void clearFields(){
        this.numberLabel.setText("");
        this.reservedLabel.setText("");
        this.tablePanel.setVisible(true);
        this.cancelButton.setVisible(true);
        int j = this.reservedTable.getRowCount();
        for(int i = 0 ; i < j ; i++){
            ((DefaultTableModel)this.reservedTable.getModel()).removeRow(0);
        }
    }
    
    public void setParentPanel(StatePanel s) { parentPanel = s; }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel codePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel numberLabel;
    private javax.swing.JLabel reservedLabel;
    private javax.swing.JTable reservedTable;
    private javax.swing.JButton returnButton;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JLabel titileLabel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JLabel tmpLabel;
    // End of variables declaration//GEN-END:variables
    
}
