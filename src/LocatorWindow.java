
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matthew
 */
public class LocatorWindow extends javax.swing.JFrame {
    
    private int newX, newY;
    private boolean tableStockClickedLast = true;

    /**
     * Creates new form LocatorWindow
     */
    public LocatorWindow() {
        
        initComponents();
        System.out.println("LocatorWindow initialised...");
        generateTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelCart = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCart = new javax.swing.JTable();
        labelStock = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableStock = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        buttonNext = new javax.swing.JButton();
        textGrandTotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        comboSortType = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelDescription = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        textDescription = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuOptions = new javax.swing.JMenu();
        menuItemChangeUser = new javax.swing.JMenuItem();
        menuItemDebugMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Item selection");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelCart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCart.setText("Cart");

        tableCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Store", "Price", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableCart.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableCart.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tableCartMouseDragged(evt);
            }
        });
        tableCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableCartMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableCartMouseReleased(evt);
            }
        });
        tableCart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableCartKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tableCart);

        labelStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelStock.setText("Available stock");

        tableStock.setAutoCreateRowSorter(true);
        tableStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Item", "Store", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableStock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableStock.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableStock.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tableStockMouseDragged(evt);
            }
        });
        tableStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableStockMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableStockMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tableStock);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(labelCart, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(labelStock)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(labelCart)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonNext.setText("Next");

        textGrandTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Grand total:");

        comboSortType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Show all items", "Only cheapest items", "Only most expensive items" }));
        comboSortType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSortTypeActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Filter by:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("(caution: this will empty your cart)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboSortType, javax.swing.GroupLayout.Alignment.TRAILING, 0, 1, Short.MAX_VALUE)
                            .addComponent(textGrandTotal)))
                    .addComponent(buttonNext, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonNext)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSortType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 299, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDescription.setText("Item description:");
        labelDescription.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jScrollPane4.setBorder(null);

        textDescription.setBackground(new java.awt.Color(255, 255, 255, 0));
        textDescription.setColumns(20);
        textDescription.setRows(5);
        textDescription.setBorder(null);
        jScrollPane4.setViewportView(textDescription);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(labelDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4)
                .addContainerGap())
        );

        menuOptions.setText("Options");

        menuItemChangeUser.setText("Change user");
        menuItemChangeUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemChangeUserActionPerformed(evt);
            }
        });
        menuOptions.add(menuItemChangeUser);

        menuItemDebugMenu.setText("Debug menu");
        menuItemDebugMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemDebugMenuActionPerformed(evt);
            }
        });
        menuOptions.add(menuItemDebugMenu);

        jMenuBar1.add(menuOptions);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tableStockMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableStockMouseDragged
        newX = evt.getX();
        newY = evt.getY();        
    }//GEN-LAST:event_tableStockMouseDragged

    private void tableStockMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableStockMouseReleased
        
        //handles moving item from one table to another
        if ((newX > -438 && newX < -45) && (newY > 0 && newY < 400)) {
            //cart <-- stock
            DefaultTableModel modelCart = (DefaultTableModel) tableCart.getModel();
            int rowSelectedIndex = tableStock.getSelectedRow();
            int rowCount = modelCart.getRowCount();
            modelCart.addRow(new String[4]);
            
            for (int i=0; i < 3; i++) {
                String value = (String) tableStock.getValueAt(rowSelectedIndex, i);
                modelCart.setValueAt(value, rowCount, i);
            }
            modelCart.setValueAt("1", rowCount, 3);
            
            DefaultTableModel modelStock = (DefaultTableModel) tableStock.getModel();
            
            modelStock.removeRow(tableStock.convertRowIndexToModel(rowSelectedIndex));
            modelStock.fireTableDataChanged();
            
            modelCart.fireTableDataChanged();
            
            updateGrandTotal();
        }
        newX = -500;
        newY = -500;
    }//GEN-LAST:event_tableStockMouseReleased

    private void tableCartMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCartMouseDragged
        newX = evt.getX();
        newY = evt.getY();
    }//GEN-LAST:event_tableCartMouseDragged

    private void tableCartMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCartMouseReleased
        
        //handles moving item from one table to another
        if ((newX > 436 && newX < 814) && (newY > 0 && newY < 400)) {
            //cart --> stock
            DefaultTableModel modelStock = (DefaultTableModel) tableStock.getModel();
            int rowSelectedIndex = tableCart.getSelectedRow();
            int rowCount = modelStock.getRowCount();
            
            for (int i=0; i < 3; i++) {
                String value = (String) tableCart.getValueAt(rowSelectedIndex, i);
                modelStock.setValueAt(value, rowCount - 1, i);
            }
            
            DefaultTableModel modelCart = (DefaultTableModel) tableCart.getModel();
            
            modelCart.removeRow(tableCart.convertRowIndexToModel(rowSelectedIndex));
            modelCart.fireTableDataChanged();
            
            modelStock.addRow(new String[3]);
            modelStock.fireTableDataChanged();
            
            updateGrandTotal();
        }
        newX = -500;
        newY = -500;        
    }//GEN-LAST:event_tableCartMouseReleased

    private void tableCartMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCartMousePressed
        tableStockClickedLast = false;
        setDescription();
        updateGrandTotal();
    }//GEN-LAST:event_tableCartMousePressed

    private void tableStockMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableStockMousePressed
        tableStockClickedLast = true;
        setDescription();
        updateGrandTotal();
    }//GEN-LAST:event_tableStockMousePressed

    private void tableCartKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableCartKeyReleased
        tableStockClickedLast = false;
        setDescription();
        updateGrandTotal();
    }//GEN-LAST:event_tableCartKeyReleased

    private void comboSortTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSortTypeActionPerformed
        generateTable();
        ((DefaultTableModel) tableCart.getModel()).setRowCount(0);
        ((DefaultTableModel) tableCart.getModel()).fireTableDataChanged();
    }//GEN-LAST:event_comboSortTypeActionPerformed

    private void menuItemChangeUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemChangeUserActionPerformed
        LoginWindow.main(new String[0]);
        this.dispose();
        System.out.println("Loading LoginWindow...");
    }//GEN-LAST:event_menuItemChangeUserActionPerformed

    private void menuItemDebugMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemDebugMenuActionPerformed
        testFrame.main(new String[0]);
        this.dispose();
        System.out.println("Loading testFrame...");
    }//GEN-LAST:event_menuItemDebugMenuActionPerformed

    private void generateTable() {        
        String sortType;
        
        switch (comboSortType.getSelectedIndex()) {
            case 0: sortType = "SORTAllItems"; break;
            case 1: sortType = "SORTCheapestItems"; break;
            case 2: sortType = "SORTMostExpensiveItems"; break;
            default: sortType = "SORTAllItems"; break;
        }
        
        //use the SwingWorker here, and disable the sort combo box until it is done processing
        
        TableModelMaker.updateTable("SELECT * FROM " + sortType, true, tableStock);
        //new TableGenWorker(sortType, true, tableStock).execute();
        
        System.out.println("LocatorWindow tables generated...");
        
        tableStock.getRowSorter().toggleSortOrder(0);
        setColumnWidths();
        
        System.out.println("LocatorWindow tables adjusted...");
    }
    
    private void updateGrandTotal() {
        double grandTotal = 0;
        int rowCount = tableCart.getRowCount();
        
        for (int i=0; i < rowCount; i++) {
            grandTotal += (( Double.parseDouble( (String) tableCart.getValueAt(i, 2) ) ) * ( Integer.parseInt( (String) tableCart.getValueAt(i, 3) ) ));
        }
        
        grandTotal = Math.round(grandTotal * 100);
        grandTotal /= 100;
        
        textGrandTotal.setText("R " + grandTotal);
    }
    
    private void setDescription() {
        String desc;
        
        if (tableStockClickedLast) {
            String itemName = (String) tableStock.getValueAt(tableStock.getSelectedRow(), 0);
            
            String query = "SELECT ItemDescription FROM tblItems WHERE ItemName = \"" + itemName + "\";";
            
            desc = new DatabaseManager().getString(query, 1, 1);
        }
        else {
            String itemName = (String) tableCart.getValueAt(tableCart.getSelectedRow(), 0);
            
            String query = "SELECT ItemDescription FROM tblItems WHERE ItemName = \"" + itemName + "\";";
            
            desc = new DatabaseManager().getString(query, 1, 1);
        }
        
        textDescription.setText(desc);
    }
    
    private void setColumnWidths() {
        for (int i=0; i < 4; i++) {
            TableColumn column = tableCart.getColumnModel().getColumn(i);
            
            switch (i) {
                case 0: column.setPreferredWidth(120); break;
                case 1: column.setPreferredWidth(70); break;
                default: column.setPreferredWidth(WIDTH);
            }
        }
        
        for (int i=0; i < 3; i++) {
            TableColumn column = tableStock.getColumnModel().getColumn(i);
            
            switch (i) {
                case 0: column.setPreferredWidth(200); break;
                case 1: column.setPreferredWidth(50); break;
                default: column.setPreferredWidth(WIDTH);
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LocatorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LocatorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LocatorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LocatorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LocatorWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonNext;
    private javax.swing.JComboBox<String> comboSortType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelCart;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelStock;
    private javax.swing.JMenuItem menuItemChangeUser;
    private javax.swing.JMenuItem menuItemDebugMenu;
    private javax.swing.JMenu menuOptions;
    private javax.swing.JTable tableCart;
    private javax.swing.JTable tableStock;
    private javax.swing.JTextArea textDescription;
    private javax.swing.JTextField textGrandTotal;
    // End of variables declaration//GEN-END:variables
}

class MyRenderer extends DefaultListCellRenderer
{
  public Component getListCellRendererComponent(JList list,Object value,
                      int index,boolean isSelected,boolean cellHasFocus)
  {
      JLabel lbl = (JLabel)super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
    lbl.setText((String)value);
    lbl.setOpaque(false);
    return lbl;
  }
}