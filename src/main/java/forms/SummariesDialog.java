package forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import model.Sale;
import utils.Json;
import utils.Mask;
import utils.Search;

/**
 *
 * @author xnilm
 */
public class SummariesDialog extends javax.swing.JDialog {

    private static Sale selectedSale;

    //
    public static Sale getSelectedSale() {
        return selectedSale;
    }

    public SummariesDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonCancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableInProgressSales = new javax.swing.JTable();
        buttonRefresh = new javax.swing.JButton();
        fieldSearchCurrent = new javax.swing.JTextField();
        buttonSearch = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fieldSearchCompleted = new javax.swing.JTextField();
        buttonSearchCompleted = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCompletedSales = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ANNA PEGOVA - RESUMOS");
        setPreferredSize(new java.awt.Dimension(1256, 650));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        buttonCancel.setBackground(new java.awt.Color(255, 51, 51));
        buttonCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonCancel.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancel.setText("CANCELAR");
        buttonCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonCancel.setFocusPainted(false);
        buttonCancel.setPreferredSize(new java.awt.Dimension(106, 35));
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        tableInProgressSales.setRowHeight(25);
        tableInProgressSales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TOTAL", "CLIENTE", "PROX. PARCELA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableInProgressSales.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableInProgressSales.setShowGrid(false);
        tableInProgressSales.getTableHeader().setReorderingAllowed(false);
        tableInProgressSales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableInProgressSalesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableInProgressSales);
        if (tableInProgressSales.getColumnModel().getColumnCount() > 0) {
            tableInProgressSales.getColumnModel().getColumn(0).setMinWidth(50);
            tableInProgressSales.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableInProgressSales.getColumnModel().getColumn(0).setMaxWidth(50);
            tableInProgressSales.getColumnModel().getColumn(1).setMinWidth(130);
            tableInProgressSales.getColumnModel().getColumn(1).setPreferredWidth(130);
            tableInProgressSales.getColumnModel().getColumn(1).setMaxWidth(130);
            tableInProgressSales.getColumnModel().getColumn(3).setMinWidth(120);
            tableInProgressSales.getColumnModel().getColumn(3).setPreferredWidth(120);
            tableInProgressSales.getColumnModel().getColumn(3).setMaxWidth(120);
        }

        buttonRefresh.setBackground(new java.awt.Color(88, 154, 89));
        buttonRefresh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonRefresh.setForeground(new java.awt.Color(255, 255, 255));
        buttonRefresh.setText("ATUALIZAR");
        buttonRefresh.setToolTipText("Recarregar");
        buttonRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonRefresh.setFocusPainted(false);
        buttonRefresh.setMaximumSize(new java.awt.Dimension(36, 36));
        buttonRefresh.setMinimumSize(new java.awt.Dimension(36, 36));
        buttonRefresh.setPreferredSize(new java.awt.Dimension(106, 35));
        buttonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshActionPerformed(evt);
            }
        });

        fieldSearchCurrent.setMaximumSize(new java.awt.Dimension(350, 35));
        fieldSearchCurrent.setMinimumSize(new java.awt.Dimension(350, 35));
        fieldSearchCurrent.setPreferredSize(new java.awt.Dimension(350, 35));

        buttonSearch.setBackground(new java.awt.Color(0, 128, 163));
        buttonSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonSearch.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearch.setText("BUSCAR");
        buttonSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonSearch.setFocusPainted(false);
        buttonSearch.setPreferredSize(new java.awt.Dimension(106, 35));
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(14, 171, 170));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VENDAS EM ANDAMENTO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        fieldSearchCompleted.setMaximumSize(new java.awt.Dimension(350, 35));
        fieldSearchCompleted.setMinimumSize(new java.awt.Dimension(350, 35));
        fieldSearchCompleted.setPreferredSize(new java.awt.Dimension(350, 35));

        buttonSearchCompleted.setBackground(new java.awt.Color(0, 128, 163));
        buttonSearchCompleted.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonSearchCompleted.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearchCompleted.setText("BUSCAR");
        buttonSearchCompleted.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonSearchCompleted.setPreferredSize(new java.awt.Dimension(98, 35));
        buttonSearchCompleted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchCompletedActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 32)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("VENDAS FINALIZADAS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        tableCompletedSales.setRowHeight(25);
        tableCompletedSales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TOTAL", "CLIENTE", "ULTIMA PARCELA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCompletedSales.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableCompletedSales.setShowGrid(false);
        tableCompletedSales.getTableHeader().setReorderingAllowed(false);
        tableCompletedSales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableCompletedSalesMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tableCompletedSales);
        if (tableCompletedSales.getColumnModel().getColumnCount() > 0) {
            tableCompletedSales.getColumnModel().getColumn(0).setMinWidth(50);
            tableCompletedSales.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableCompletedSales.getColumnModel().getColumn(0).setMaxWidth(50);
            tableCompletedSales.getColumnModel().getColumn(1).setMinWidth(130);
            tableCompletedSales.getColumnModel().getColumn(1).setPreferredWidth(130);
            tableCompletedSales.getColumnModel().getColumn(1).setMaxWidth(130);
            tableCompletedSales.getColumnModel().getColumn(3).setMinWidth(120);
            tableCompletedSales.getColumnModel().getColumn(3).setPreferredWidth(120);
            tableCompletedSales.getColumnModel().getColumn(3).setMaxWidth(120);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(fieldSearchCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldSearchCompleted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSearchCompleted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldSearchCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonSearchCompleted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldSearchCompleted, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        if (tableInProgressSales.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Você deve selecionar um item da lista!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int selectedRow = tableInProgressSales.getSelectedRow();
        int saleId = (Integer) tableInProgressSales.getValueAt(selectedRow, 0);

        try {
            Json.updateStockFromCanceledSale(saleId);
        } catch (IOException ex) {
            Logger.getLogger(SummariesDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao mudar quantidade no estoque a partir do cancelamento da compra!",
                    "ERRO!", JOptionPane.ERROR_MESSAGE);
            return;

        }

        try {
            Json.deleteIndexFromJson(tableInProgressSales, Json.getSalesFileLocation(), 2);
        } catch (IOException ex) {
            Logger.getLogger(SummariesDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao cancelar compra!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshActionPerformed
        try {
            Mask.clearSearchFieldsSummaries(fieldSearchCurrent, fieldSearchCompleted);
            Json.refreshSummariesTables(tableInProgressSales, tableCompletedSales);
            JOptionPane.showMessageDialog(null, "Tabelas atualizadas!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            Logger.getLogger(SummariesDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao atualizar tabela! (nenhuma venda foi realizada ainda)",
                    "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonRefreshActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        try {
            Search.searchItemOnTable(tableInProgressSales, fieldSearchCurrent.getText(), 2);
        } catch (IOException ex) {
            Logger.getLogger(SummariesDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar!",
                    "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void buttonSearchCompletedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCompletedActionPerformed
        try {
            Search.searchItemOnTable(tableCompletedSales, fieldSearchCompleted.getText(), 3);
        } catch (IOException ex) {
            Logger.getLogger(SummariesDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar! (nenhuma venda foi realizada ainda)",
                    "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonSearchCompletedActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            Json.refreshSummariesTables(tableInProgressSales, tableCompletedSales);
        } catch (IOException ex) {
            Logger.getLogger(SummariesDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao atualizar tabela! (nenhuma venda foi realizada ainda)",
                    "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_formWindowOpened

    private void tableInProgressSalesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableInProgressSalesMousePressed
        if (!SwingUtilities.isRightMouseButton(evt)) {
            return;
        }

        int row = tableInProgressSales.rowAtPoint(evt.getPoint());

        if (row < 0) {
            return;
        }

        tableInProgressSales.setRowSelectionInterval(row, row);
        tableInProgressSales.setFocusable(true);
        tableInProgressSales.requestFocusInWindow();

        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem viewItem = new JMenuItem("Visualizar compra");
        viewItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int saleId = (Integer) tableInProgressSales.getValueAt(row, 0);

                Sale sale = null;

                try {
                    sale = (Sale) Json.returnRowAsObject(saleId, 2);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "ERRO AO ABRIR PAINEL DE VISUALIZAÇÃO!", "ERRO!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                selectedSale = sale;

                ModalViewInProgressSale modalView = new ModalViewInProgressSale(SummariesDialog.this, true);
                modalView.setVisible(true);
            }
        });

        popupMenu.add(viewItem);
        popupMenu.show(tableInProgressSales, evt.getX(), evt.getY());
    }//GEN-LAST:event_tableInProgressSalesMousePressed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        try {
            Json.refreshSummariesTables(tableInProgressSales, tableCompletedSales);
        } catch (IOException ex) {
            Logger.getLogger(SummariesDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_formWindowGainedFocus

    private void tableCompletedSalesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCompletedSalesMousePressed
        if (!SwingUtilities.isRightMouseButton(evt)) {
            return;
        }

        int row = tableCompletedSales.rowAtPoint(evt.getPoint());

        if (row < 0) {
            return;
        }

        tableCompletedSales.setRowSelectionInterval(row, row);
        tableCompletedSales.setFocusable(true);
        tableCompletedSales.requestFocusInWindow();

        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem viewItem = new JMenuItem("Visualizar compra");
        viewItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int saleId = (Integer) tableCompletedSales.getValueAt(row, 0);

                Sale sale = null;

                try {
                    sale = (Sale) Json.returnRowAsObject(saleId, 3);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "ERRO AO ABRIR PAINEL DE VISUALIZAÇÃO!", "ERRO!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                selectedSale = sale;

                ModalViewCompletedSale modalView = new ModalViewCompletedSale(SummariesDialog.this, true);
                modalView.setVisible(true);
            }
        });

        popupMenu.add(viewItem);
        popupMenu.show(tableCompletedSales, evt.getX(), evt.getY());

    }//GEN-LAST:event_tableCompletedSalesMousePressed

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
            java.util.logging.Logger.getLogger(SummariesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SummariesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SummariesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SummariesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SummariesDialog dialog = new SummariesDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JButton buttonSearchCompleted;
    private javax.swing.JTextField fieldSearchCompleted;
    private javax.swing.JTextField fieldSearchCurrent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableCompletedSales;
    private javax.swing.JTable tableInProgressSales;
    // End of variables declaration//GEN-END:variables
}
