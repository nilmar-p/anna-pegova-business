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
import model.Client;
import utils.Json;
import utils.Mask;
import utils.Search;

public class ClientsDialog extends javax.swing.JDialog {

    private static Client selectedClient;

    //
    public static Client getSelectedClient() {
        return selectedClient;
    }

    //
    public ClientsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonRefresh = new javax.swing.JButton();
        fieldSearch = new javax.swing.JTextField();
        buttonSearch = new javax.swing.JButton();
        comboFilter = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        buttonDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRegistereds = new javax.swing.JTable();
        buttonNewClient = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ANNA PEGOVA - LISTA DE CLIENTES");
        setPreferredSize(new java.awt.Dimension(1000, 600));
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

        buttonRefresh.setBackground(new java.awt.Color(88, 154, 89));
        buttonRefresh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonRefresh.setForeground(new java.awt.Color(255, 255, 255));
        buttonRefresh.setText("A");
        buttonRefresh.setToolTipText("Recarregar");
        buttonRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonRefresh.setFocusPainted(false);
        buttonRefresh.setMaximumSize(new java.awt.Dimension(36, 36));
        buttonRefresh.setMinimumSize(new java.awt.Dimension(36, 36));
        buttonRefresh.setPreferredSize(new java.awt.Dimension(36, 36));
        buttonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshActionPerformed(evt);
            }
        });

        fieldSearch.setMaximumSize(new java.awt.Dimension(350, 35));
        fieldSearch.setMinimumSize(new java.awt.Dimension(350, 35));
        fieldSearch.setPreferredSize(new java.awt.Dimension(350, 35));

        buttonSearch.setBackground(new java.awt.Color(0, 128, 163));
        buttonSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonSearch.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearch.setText("BUSCAR");
        buttonSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonSearch.setFocusPainted(false);
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        comboFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOME", "CIDADE" }));
        comboFilter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFilterActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("FILTRO:");

        buttonDelete.setBackground(new java.awt.Color(255, 51, 51));
        buttonDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonDelete.setForeground(new java.awt.Color(255, 255, 255));
        buttonDelete.setText("DELETAR");
        buttonDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonDelete.setFocusPainted(false);
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        tableRegistereds.setRowHeight(25);
        tableRegistereds.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "TELEFONE", "CPF", "CIDADE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableRegistereds.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableRegistereds.setShowGrid(false);
        tableRegistereds.getTableHeader().setReorderingAllowed(false);
        tableRegistereds.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableRegisteredsMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableRegistereds);
        if (tableRegistereds.getColumnModel().getColumnCount() > 0) {
            tableRegistereds.getColumnModel().getColumn(0).setMinWidth(55);
            tableRegistereds.getColumnModel().getColumn(0).setPreferredWidth(55);
            tableRegistereds.getColumnModel().getColumn(0).setMaxWidth(55);
            tableRegistereds.getColumnModel().getColumn(1).setMinWidth(450);
            tableRegistereds.getColumnModel().getColumn(1).setPreferredWidth(450);
            tableRegistereds.getColumnModel().getColumn(1).setMaxWidth(450);
            tableRegistereds.getColumnModel().getColumn(2).setResizable(false);
            tableRegistereds.getColumnModel().getColumn(3).setResizable(false);
            tableRegistereds.getColumnModel().getColumn(4).setResizable(false);
        }

        buttonNewClient.setBackground(new java.awt.Color(14, 171, 170));
        buttonNewClient.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonNewClient.setForeground(new java.awt.Color(255, 255, 255));
        buttonNewClient.setText("NOVO CLIENTE");
        buttonNewClient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonNewClient.setFocusPainted(false);
        buttonNewClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewClientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonNewClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(comboFilter, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonRefresh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonNewClient, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshActionPerformed
        try {
            Mask.clearSearchField(fieldSearch);
            Json.refreshTableByType(tableRegistereds, 1);
            JOptionPane.showMessageDialog(null, "Tabela atualizada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            Logger.getLogger(ClientsDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao atualizar tabela!", "ERRO!", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_buttonRefreshActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        try {
            Search.searchClientsOnTable(tableRegistereds, fieldSearch.getText().toUpperCase(), comboFilter.getSelectedItem().toString());
        } catch (IOException ex) {
            Logger.getLogger(ClientsDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar!",
                    "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void comboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboFilterActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        try {
            Json.deleteIndexFromJson(tableRegistereds, Json.getClientsFileLocation(), 1);
        } catch (IOException ex) {
            Logger.getLogger(ClientsDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonNewClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewClientActionPerformed
        NewClientDialog modalNew = new NewClientDialog(this, true);
        modalNew.setVisible(true);
    }//GEN-LAST:event_buttonNewClientActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        try {
            Json.refreshTableByType(tableRegistereds, 1);
        } catch (IOException ex) {
            Logger.getLogger(ClientsDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_formWindowOpened

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        try {
            Json.refreshTableByType(tableRegistereds, 1);
        } catch (IOException ex) {
            Logger.getLogger(ClientsDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void tableRegisteredsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableRegisteredsMousePressed
        if (!SwingUtilities.isRightMouseButton(evt)) {
            return;
        }

        int row = tableRegistereds.rowAtPoint(evt.getPoint());

        if (row < 0) {
            return;
        }

        tableRegistereds.setRowSelectionInterval(row, row);
        tableRegistereds.setFocusable(true);
        tableRegistereds.requestFocusInWindow();

        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem viewItem = new JMenuItem("Visualizar cliente");
        viewItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int clientId = (Integer) tableRegistereds.getValueAt(row, 0);

                Client client = null;

                try {
                    client = (Client) Json.returnRowAsObject(clientId, 1);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "ERRO AO ABRIR PAINEL DE VISUALIZAÇÃO!", "ERRO!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                selectedClient = client;

                ModalViewClient modalView = new ModalViewClient(ClientsDialog.this, true);
                modalView.setVisible(true);
            }
        });

        JMenuItem editItem = new JMenuItem("Editar cliente");
        editItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int clientId = (Integer) tableRegistereds.getValueAt(row, 0);

                Client client = null;

                try {
                    client = (Client) Json.returnRowAsObject(clientId, 1);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "ERRO AO ABRIR PAINEL DE EDIÇÃO!", "ERRO!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                selectedClient = client;

                ModalEditClient modalView = new ModalEditClient(ClientsDialog.this, true);
                modalView.setVisible(true);
            }
        });

        popupMenu.add(viewItem);
        popupMenu.add(editItem);
        popupMenu.show(tableRegistereds, evt.getX(), evt.getY());

    }//GEN-LAST:event_tableRegisteredsMousePressed

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
            java.util.logging.Logger.getLogger(ClientsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClientsDialog dialog = new ClientsDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonNewClient;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JComboBox<String> comboFilter;
    private javax.swing.JTextField fieldSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableRegistereds;
    // End of variables declaration//GEN-END:variables
}
