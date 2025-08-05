package forms;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Toolkit;
import javax.swing.UIManager;

public class Menu extends javax.swing.JFrame {

    public Menu() {
        initComponents();
        setSize(new java.awt.Dimension(1050, 650));
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        buttonSell = new javax.swing.JButton();
        buttonStock = new javax.swing.JButton();
        buttonSummaries = new javax.swing.JButton();
        buttonClients = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel9.setText("jLabel9");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ANNA PEGOVA - MENU INICIAL");
        setMinimumSize(new java.awt.Dimension(1050, 650));
        setResizable(false);
        setSize(new java.awt.Dimension(1050, 650));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(14, 171, 170));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 42)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ANNA PEGOVA - CONTROLE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        buttonSell.setBackground(new java.awt.Color(0, 128, 163));
        buttonSell.setFont(new java.awt.Font("Segoe UI Semibold", 0, 30)); // NOI18N
        buttonSell.setForeground(new java.awt.Color(255, 255, 255));
        buttonSell.setText("VENDER");
        buttonSell.setBorder(null);
        buttonSell.setBorderPainted(false);
        buttonSell.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonSell.setFocusPainted(false);
        buttonSell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSellActionPerformed(evt);
            }
        });
        getContentPane().add(buttonSell, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 222, 90));

        buttonStock.setBackground(new java.awt.Color(0, 128, 163));
        buttonStock.setFont(new java.awt.Font("Segoe UI Semibold", 0, 30)); // NOI18N
        buttonStock.setForeground(new java.awt.Color(255, 255, 255));
        buttonStock.setText("ESTOQUE");
        buttonStock.setBorder(null);
        buttonStock.setBorderPainted(false);
        buttonStock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonStock.setFocusPainted(false);
        buttonStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStockActionPerformed(evt);
            }
        });
        getContentPane().add(buttonStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 222, 90));

        buttonSummaries.setBackground(new java.awt.Color(0, 128, 163));
        buttonSummaries.setFont(new java.awt.Font("Segoe UI Semibold", 0, 30)); // NOI18N
        buttonSummaries.setForeground(new java.awt.Color(255, 255, 255));
        buttonSummaries.setText("RESUMOS");
        buttonSummaries.setBorder(null);
        buttonSummaries.setBorderPainted(false);
        buttonSummaries.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonSummaries.setFocusPainted(false);
        buttonSummaries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSummariesActionPerformed(evt);
            }
        });
        getContentPane().add(buttonSummaries, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 222, 90));

        buttonClients.setBackground(new java.awt.Color(0, 128, 163));
        buttonClients.setFont(new java.awt.Font("Segoe UI Semibold", 0, 30)); // NOI18N
        buttonClients.setForeground(new java.awt.Color(255, 255, 255));
        buttonClients.setText("CLIENTES");
        buttonClients.setBorder(null);
        buttonClients.setBorderPainted(false);
        buttonClients.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonClients.setFocusPainted(false);
        buttonClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClientsActionPerformed(evt);
            }
        });
        getContentPane().add(buttonClients, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 222, 90));

        jPanel2.setPreferredSize(new java.awt.Dimension(710, 288));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Bem-vindo(a)!");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Selecione uma opção.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 710, 420));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSellActionPerformed
        SaleDialog modalSale = new SaleDialog(this, true);
        modalSale.setVisible(true);
    }//GEN-LAST:event_buttonSellActionPerformed

    private void buttonStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStockActionPerformed
        StockDialog modalStock = new StockDialog(this, true);
        modalStock.setVisible(true);

    }//GEN-LAST:event_buttonStockActionPerformed

    private void buttonSummariesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSummariesActionPerformed
        SummariesDialog summariesDialog = new SummariesDialog(this, true);

        summariesDialog.setVisible(true);
    }//GEN-LAST:event_buttonSummariesActionPerformed

    private void buttonClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClientsActionPerformed
        ClientsDialog modalClients = new ClientsDialog(this, true);
        modalClients.setVisible(true);
    }//GEN-LAST:event_buttonClientsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            Menu frame = new Menu();
            frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClients;
    private javax.swing.JButton buttonSell;
    private javax.swing.JButton buttonStock;
    private javax.swing.JButton buttonSummaries;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
