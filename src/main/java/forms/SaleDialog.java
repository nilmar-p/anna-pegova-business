package forms;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.Client;
import model.Product;
import model.ProductSold;
import utils.Json;
import utils.Mask;
import utils.SaleFunctions;

/**
 *
 * @author xnilm
 */
public class SaleDialog extends javax.swing.JDialog {
    
    private static double totalSale;
    private final List<Product> filteredProducts = new ArrayList<>();
    private final List<Client> filteredClients = new ArrayList<>();
    
    private static final List<ProductSold> selectedProducts = new ArrayList<>();
    private static Client selectedClient;
    //

    public static List<ProductSold> getSelectedProducts() {
        return selectedProducts;
    }
    
    public static Client getSelectedClient() {
        return selectedClient;
    }
    
    public static double getTotalSale() {
        return totalSale;
    }

    //
    public SaleDialog(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboProducts = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        spinnerAmount = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        comboClients = new javax.swing.JComboBox<>();
        buttonAddProduct = new javax.swing.JButton();
        buttonProceed = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSaleSummary = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fieldSaleTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REALIZAR VENDA");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(14, 171, 170));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PAINEL DE VENDA");
        jLabel1.setPreferredSize(new java.awt.Dimension(123, 65));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel2AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Produto:");

        comboProducts.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        comboProducts.setForeground(new java.awt.Color(51, 51, 51));
        comboProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProductsActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Quantidade:");

        spinnerAmount.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinnerAmount.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerAmountStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Cliente:");

        comboClients.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        comboClients.setForeground(new java.awt.Color(51, 51, 51));
        comboClients.setPreferredSize(new java.awt.Dimension(72, 35));
        comboClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClientsActionPerformed(evt);
            }
        });

        buttonAddProduct.setBackground(new java.awt.Color(88, 154, 89));
        buttonAddProduct.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        buttonAddProduct.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddProduct.setText("ADICIONAR (+)");
        buttonAddProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonAddProduct.setFocusPainted(false);
        buttonAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboClients, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboProducts, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 257, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buttonAddProduct)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinnerAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboClients, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(buttonAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonProceed.setBackground(new java.awt.Color(0, 128, 163));
        buttonProceed.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        buttonProceed.setForeground(new java.awt.Color(255, 255, 255));
        buttonProceed.setText("PROSSEGUIR");
        buttonProceed.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonProceed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProceedActionPerformed(evt);
            }
        });

        tableSaleSummary.setRowHeight(25);
        tableSaleSummary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "PRODUTO", "QTD.", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSaleSummary.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tableSaleSummaryComponentAdded(evt);
            }
        });
        tableSaleSummary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableSaleSummaryMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableSaleSummary);
        if (tableSaleSummary.getColumnModel().getColumnCount() > 0) {
            tableSaleSummary.getColumnModel().getColumn(0).setMinWidth(50);
            tableSaleSummary.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableSaleSummary.getColumnModel().getColumn(0).setMaxWidth(50);
            tableSaleSummary.getColumnModel().getColumn(3).setMinWidth(80);
            tableSaleSummary.getColumnModel().getColumn(3).setPreferredWidth(80);
            tableSaleSummary.getColumnModel().getColumn(3).setMaxWidth(80);
        }

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("TOTAL:");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("R$");

        fieldSaleTotal.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        fieldSaleTotal.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldSaleTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldSaleTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonProceed, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonProceed, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClientsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboClientsActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        totalSale = 0;
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        try {
            String contentP = Files.exists(Json.getProductsFileLocation())
                    ? Files.readString(Json.getProductsFileLocation())
                    : "[]";
            
            String contentC = Files.exists(Json.getClientsFileLocation())
                    ? Files.readString(Json.getClientsFileLocation())
                    : "[]";
            
            List<Product> products = mapper.readValue(contentP, new TypeReference<>() {
            });
            
            List<Client> clients = mapper.readValue(contentC, new TypeReference<>() {
            });
            
            for (Product product : products) {
                if (product.getAmount() > 0) {
                    filteredProducts.add(product);
                }
            }
            
            for (Client client : clients) {
                filteredClients.add(client);
            }
            
            Mask.refreshSaleComboBox(comboProducts, filteredProducts, 0);
            Mask.refreshSaleComboBox(comboClients, filteredClients, 1);
            
        } catch (IOException ex) {
            Logger.getLogger(SaleDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void comboProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProductsActionPerformed
        if (comboProducts.getItemCount() != filteredProducts.size()) {
            return;
        }
        
        if (comboProducts.getSelectedItem() == null) {
            spinnerAmount.setEnabled(false);
            return;
        }
        
        int selectedIndex = comboProducts.getSelectedIndex();
        
        if (selectedIndex < 0 || selectedIndex >= filteredProducts.size()) {
            spinnerAmount.setEnabled(false);
            return;
        }
        
        Product selected = filteredProducts.get(selectedIndex);
        
        if (selected.getAmount() <= 0) {
            spinnerAmount.setEnabled(false);
            return;
        }
        
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(1, 1, selected.getAmount(), 1);
        spinnerAmount.setModel(spinnerNumberModel);
        spinnerAmount.setEnabled(true);
    }//GEN-LAST:event_comboProductsActionPerformed

    private void buttonProceedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProceedActionPerformed
        selectedProducts.clear();
        
        if (tableSaleSummary.getRowCount() < 1) {
            JOptionPane.showMessageDialog(null, "Adicione pelo menos um item antes de prosseguir!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        for (int i = 0; i < tableSaleSummary.getRowCount(); i++) {
            int id = ((Integer) tableSaleSummary.getValueAt(i, 0));
            String name = ((String) tableSaleSummary.getValueAt(i, 1));
            int quantity = ((Integer) tableSaleSummary.getValueAt(i, 2));
            double total = (double) tableSaleSummary.getValueAt(i, 3);
            
            selectedProducts.add(new ProductSold(id, name, quantity, total));
        }
        
        int selectedClientId = Integer.parseInt(comboClients.getSelectedItem().toString().substring(0, 5));
        
        ObjectMapper mapper = new ObjectMapper();
        List<Client> clients = new ArrayList<>();
        try {
            clients = mapper.readValue(new File(String.valueOf(Json.getClientsFileLocation())), new TypeReference<>() {
            });
        } catch (IOException ex) {
            Logger.getLogger(SaleDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (Client client : clients) {
            if (client.getId() == selectedClientId) {
                selectedClient = client;
                break;
            }
        }
        
        ModalSaleFinalize modalSaleFinalize = new ModalSaleFinalize(SaleDialog.this, true);
        modalSaleFinalize.setVisible(true);
        
        if (modalSaleFinalize.isPucharseCompleted) {
            dispose();
        }
    }//GEN-LAST:event_buttonProceedActionPerformed

    private void spinnerAmountStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerAmountStateChanged

    }//GEN-LAST:event_spinnerAmountStateChanged

    private void buttonAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddProductActionPerformed
        
        if (comboClients.getSelectedItem() == null || comboProducts.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int selectedProductId = Integer.parseInt(comboProducts.getSelectedItem().toString().substring(0, 5));
        
        Product selectedProduct = null;
        for (Product filteredProduct : filteredProducts) {
            if (filteredProduct.getId() == selectedProductId) {
                selectedProduct = filteredProduct;
                break;
            }
        }
        
        filteredProducts.remove(selectedProduct);
        
        int amount = ((Number) spinnerAmount.getValue()).intValue();
        double grossValue = amount * selectedProduct.getPrice();
        
        if (grossValue > selectedProduct.getTotal()) {
            JOptionPane.showMessageDialog(null, "Erro na venda! (Valor bruto maior que o estoque)", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            SaleFunctions.addItemOnSaleSummary(tableSaleSummary, selectedProduct.getId(), amount, grossValue);
        } catch (IOException ex) {
            Logger.getLogger(SaleDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao adicionar item no resumo!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Mask.refreshSaleComboBox(comboProducts, filteredProducts, 0);
        
        totalSale += grossValue;
        fieldSaleTotal.setText(String.format("%.2f", totalSale));
    }//GEN-LAST:event_buttonAddProductActionPerformed

    private void jPanel2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel2AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2AncestorAdded

    private void tableSaleSummaryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSaleSummaryMousePressed
        if (!SwingUtilities.isRightMouseButton(evt)) {
            return;
        }
        
        int row = tableSaleSummary.rowAtPoint(evt.getPoint());
        
        if (row < 0) {
            return;
        }
        
        tableSaleSummary.setRowSelectionInterval(row, row);
        tableSaleSummary.setFocusable(true);
        tableSaleSummary.requestFocusInWindow();
        
        JPopupMenu popupMenu = new JPopupMenu();
        
        JMenuItem removeItem = new JMenuItem("Remover");
        removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int itemId = (Integer) tableSaleSummary.getValueAt(row, 0);
                double grossValue = (double) tableSaleSummary.getValueAt(row, 3);
                
                Product item = null;
                
                try {
                    item = (Product) Json.returnRowAsObject(itemId, 0);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao remover item!", "ERRO!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                filteredProducts.add(item);
                
                DefaultTableModel model = (DefaultTableModel) tableSaleSummary.getModel();
                model.removeRow(row);
                
                Mask.refreshSaleComboBox(comboProducts, filteredProducts, 0);
                
                totalSale -= grossValue;
                fieldSaleTotal.setText(String.format("%.2f", totalSale));
            }
        });
        
        popupMenu.add(removeItem);
        popupMenu.show(tableSaleSummary, evt.getX(), evt.getY());

    }//GEN-LAST:event_tableSaleSummaryMousePressed

    private void tableSaleSummaryComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tableSaleSummaryComponentAdded

    }//GEN-LAST:event_tableSaleSummaryComponentAdded
    
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
            java.util.logging.Logger.getLogger(SaleDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SaleDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SaleDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SaleDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SaleDialog dialog = new SaleDialog(new javax.swing.JDialog(), true);
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
    private javax.swing.JButton buttonAddProduct;
    private javax.swing.JButton buttonProceed;
    private javax.swing.JComboBox<String> comboClients;
    private javax.swing.JComboBox<String> comboProducts;
    private javax.swing.JLabel fieldSaleTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner spinnerAmount;
    private javax.swing.JTable tableSaleSummary;
    // End of variables declaration//GEN-END:variables
}
