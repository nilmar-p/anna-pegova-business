package forms;

import enums.DiscountType;
import static forms.SaleDialog.getSelectedClient;
import static forms.SaleDialog.getSelectedProducts;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import model.ProductSold;
import utils.Json;
import utils.SaleFunctions;

public class ModalSaleFinalize extends javax.swing.JDialog {

    public boolean isPucharseCompleted;

    private static double totalSale;

    //
    public static double getTotalSale() {
        return totalSale;
    }

    //
    public ModalSaleFinalize(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        buttonFinishSale = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        spinnerInstallment = new javax.swing.JSpinner();
        labelInstallment = new javax.swing.JLabel();
        checkInstallment = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        dateBillingDate = new com.toedter.calendar.JDateChooser();
        comboDiscount = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        labelDiscountValue = new javax.swing.JLabel();
        spinnerDiscountValue = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FINALIZAR VENDA");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(14, 171, 170));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("APLICAR DESCONTO");
        jLabel1.setPreferredSize(new java.awt.Dimension(123, 65));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        buttonFinishSale.setBackground(new java.awt.Color(0, 128, 163));
        buttonFinishSale.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        buttonFinishSale.setForeground(new java.awt.Color(255, 255, 255));
        buttonFinishSale.setText("FINALIZAR");
        buttonFinishSale.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonFinishSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFinishSaleActionPerformed(evt);
            }
        });

        spinnerInstallment.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1, 1));
        spinnerInstallment.setEnabled(false);

        labelInstallment.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        labelInstallment.setForeground(new java.awt.Color(51, 51, 51));
        labelInstallment.setText("N°");
        labelInstallment.setEnabled(false);

        checkInstallment.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        checkInstallment.setForeground(new java.awt.Color(51, 51, 51));
        checkInstallment.setText("Parcelamento");
        checkInstallment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkInstallmentActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Data de cobrança:");

        comboDiscount.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        comboDiscount.setForeground(new java.awt.Color(51, 51, 51));
        comboDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDiscountActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Desconto:");

        labelDiscountValue.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        labelDiscountValue.setForeground(new java.awt.Color(51, 51, 51));
        labelDiscountValue.setText("Valor:");
        labelDiscountValue.setAutoscrolls(true);
        labelDiscountValue.setEnabled(false);

        spinnerDiscountValue.setModel(new javax.swing.SpinnerNumberModel(1.0d, 1.0d, null, 1.0d));
        spinnerDiscountValue.setAutoscrolls(true);
        spinnerDiscountValue.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comboDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelDiscountValue)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(spinnerDiscountValue, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(checkInstallment)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelInstallment)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(spinnerInstallment, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dateBillingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(200, 200, 200)))
                    .addGap(35, 35, 35)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(75, 75, 75)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDiscountValue, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerDiscountValue, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dateBillingDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(checkInstallment)
                        .addComponent(spinnerInstallment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelInstallment))
                    .addContainerGap(76, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(buttonFinishSale, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonFinishSale, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonFinishSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFinishSaleActionPerformed
        if (dateBillingDate.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Você deve selecionar uma data de cobrança!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BigDecimal discountValue = BigDecimal.valueOf(((Number) spinnerDiscountValue.getValue()).doubleValue());
        BigDecimal netValue = BigDecimal.valueOf(totalSale);

        BigDecimal absoluteDiscount;
        BigDecimal percentageDiscount;

        switch (comboDiscount.getSelectedIndex()) {
            case 0 -> {
                absoluteDiscount = BigDecimal.ZERO;
                percentageDiscount = BigDecimal.ZERO;
            }
            case 1 -> {
                // Desconto em porcentagem
                percentageDiscount = discountValue;
                absoluteDiscount = BigDecimal.valueOf(totalSale)
                        .multiply(percentageDiscount)
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
                netValue = netValue.subtract(absoluteDiscount);
            }
            case 2 -> {
                // Desconto absoluto
                absoluteDiscount = discountValue;
                percentageDiscount = totalSale > 0
                        ? absoluteDiscount.divide(BigDecimal.valueOf(totalSale), 4, RoundingMode.HALF_UP)
                                .multiply(BigDecimal.valueOf(100))
                        : BigDecimal.ZERO;
                netValue = netValue.subtract(absoluteDiscount);
            }
            default ->
                throw new AssertionError();
        }

        Date selectedDate = dateBillingDate.getDate();
        LocalDate firstDueDate = selectedDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        int numberOfInstallments = 1;

        Object spinnerValue = spinnerInstallment.getValue();
        if (spinnerValue instanceof Number) {
            numberOfInstallments = ((Number) spinnerValue).intValue();
        }

        List<ProductSold> productsSold = new ArrayList<>();
        for (ProductSold actual : getSelectedProducts()) {
            productsSold.add(new ProductSold(actual.getId(), actual.getName(), actual.getQuantity(), actual.getTotal()));
        }

        try {
            isPucharseCompleted = SaleFunctions.finishSale(getSelectedClient(), productsSold,
                    numberOfInstallments, netValue, firstDueDate, absoluteDiscount, percentageDiscount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (isPucharseCompleted) {

            try {
                Json.updateStockFromSale(productsSold);
            } catch (IOException ex) {
                Logger.getLogger(ModalSaleFinalize.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao finalizar compra!", "ERRO!", JOptionPane.ERROR_MESSAGE);

                return;
            }

            JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }


    }//GEN-LAST:event_buttonFinishSaleActionPerformed

    private void checkInstallmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkInstallmentActionPerformed

        if (checkInstallment.isSelected()) {
            spinnerInstallment.setEnabled(true);
            labelInstallment.setEnabled(true);

            SpinnerNumberModel model = new SpinnerNumberModel(2, 2, 100, 1);
            spinnerInstallment.setModel(model);

        } else {
            spinnerInstallment.setEnabled(false);
            labelInstallment.setEnabled(false);

            SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 1, 1);
            spinnerInstallment.setModel(model);
        }
    }//GEN-LAST:event_checkInstallmentActionPerformed

    private void comboDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDiscountActionPerformed
        switch (comboDiscount.getSelectedIndex()) {
            case 1, 2 -> {
                labelDiscountValue.setEnabled(true);
                spinnerDiscountValue.setEnabled(true);
            }
            default -> {
                labelDiscountValue.setEnabled(false);
                spinnerDiscountValue.setEnabled(false);
            }
        }
    }//GEN-LAST:event_comboDiscountActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        isPucharseCompleted = false;

        totalSale = SaleDialog.getTotalSale();

        DefaultComboBoxModel model = new DefaultComboBoxModel(DiscountType.values());
        comboDiscount.setModel(model);
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(ModalSaleFinalize.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModalSaleFinalize.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModalSaleFinalize.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModalSaleFinalize.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModalSaleFinalize dialog = new ModalSaleFinalize(new javax.swing.JDialog(), true);
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
    private javax.swing.JButton buttonFinishSale;
    private javax.swing.JCheckBox checkInstallment;
    private javax.swing.JComboBox<String> comboDiscount;
    private com.toedter.calendar.JDateChooser dateBillingDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelDiscountValue;
    private javax.swing.JLabel labelInstallment;
    private javax.swing.JSpinner spinnerDiscountValue;
    private javax.swing.JSpinner spinnerInstallment;
    // End of variables declaration//GEN-END:variables
}
