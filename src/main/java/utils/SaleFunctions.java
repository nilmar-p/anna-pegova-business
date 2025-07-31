package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Client;
import model.Product;
import model.ProductSold;
import model.Sale;
import static utils.Json.getProductsFileLocation;

public class SaleFunctions {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static boolean finishSale(
            Client selectedClient,
            List<ProductSold> productsSold,
            int numberOfInstallments,
            BigDecimal netValue,
            LocalDate firstDueDate,
            BigDecimal absoluteDiscount,
            BigDecimal percentageDiscount
    ) throws IOException {
        StringBuilder productsHtml = new StringBuilder();

        for (ProductSold product : productsSold) {
            int qty = product.getQuantity();
            BigDecimal totalItem = product.getTotal();

            productsHtml.append(String.format(
                    """
                <tr style='border-bottom:1px solid #dee2e6;'>
                    <td style='padding:6px;'>%s</td>
                    <td style='padding:6px; text-align:center;'>%d</td>
                    <td style='padding:6px; text-align:right;'>R$ %.2f</td>
                </tr>
                """, product.getName(), qty, totalItem
            ));
        }

        BigDecimal totalGrossValue = netValue.add(absoluteDiscount);

        BigDecimal installmentValue = netValue.divide(
                BigDecimal.valueOf(numberOfInstallments), 2, RoundingMode.HALF_UP
        );

        List<LocalDate> allBillingDates = new ArrayList<>();
        LocalDate current = firstDueDate;
        for (int i = 0; i < numberOfInstallments; i++) {
            allBillingDates.add(current);
            current = current.plusMonths(1);
        }

        String msg = String.format(
                """
<html>
<body style='font-family:"Segoe UI Semibold", sans-serif; font-size:13px; color:#333;'>

<div style='width: 450px; margin: 0 auto; background-color: #f8f9fa; border: 1px solid #dee2e6; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);'>

    <div style='background-color: #0EABAA; color: #ffffff; padding: 10px 15px; border-radius: 8px 8px 0 0; text-align:center;'>
        <b style='font-size:18px;'>Resumo da Compra</b>
    </div>

    <div style='padding: 5px 15px 12px 15px;'>
        <table style='width:100%%; border-collapse:collapse;'>
            
            <tr style='border-bottom: 1px solid #e9ecef;'>
                <td colspan='3' style='padding:10px 0; text-align:center; font-size:12px; color:#495057;'>
                    Cliente: <b style='color:#212529;'>%s</b> &nbsp;&bull;&nbsp; CPF: <b style='color:#212529;'>%s</b>
                </td>
            </tr>

            <tr>
                <th style='padding:8px; text-align:left; color:#6c757d;'>Produto</th>
                <th style='padding:8px; text-align:center; color:#6c757d;'>Qtd</th>
                <th style='padding:8px; text-align:right; color:#6c757d;'>Total</th>
            </tr>

            %s <!-- Produtos listados dinamicamente -->

            <tr>
                <td colspan='2' style='padding:8px 0; text-align:right; color:#6c757d;'>Valor Bruto:</td>
                <td style='padding:8px 0; text-align:right; color:#212529;'>R$ %.2f</td>
            </tr>
            <tr>
                <td colspan='2' style='padding:4px 0; text-align:right; color:#6c757d;'>Desconto:</td>
                <td style='padding:4px 0; text-align:right; color:#dc3545;'>- R$ %.2f (%.2f%%)</td>
            </tr>
            <tr>
                <td colspan='2' style='padding:12px 0; text-align:right; font-size:12px; color:#6c757d;'>VALOR TOTAL A PAGAR</td>
                <td style='padding:12px 0; text-align:right; font-size:20px; font-weight:bold; color:#28a745;'>R$ %.2f</td>
            </tr>

            <tr>
                <td colspan='3' style='background-color: #f0f4f8; padding: 10px; border-radius: 6px; text-align:center;'>
                    <div style='font-size:14px; color:#0056b3; margin-bottom:5px;'>
                        <b>%d</b> parcelas de <b style='font-size:15px;'>R$ %.2f</b>
                    </div>
                    <div style='font-size:12px; color:#495057;'>
                        1ª cobrança: <b>%s</b> &nbsp;|&nbsp; Última: <b>%s</b>
                    </div>
                </td>
            </tr>
        </table>
    </div>

    <div style='background-color: #f1f3f5; text-align:center; font-size:13px; color:#495057; padding: 10px; border-radius: 0 0 8px 8px; border-top: 1px solid #dee2e6;'>
        <b>Deseja finalizar a venda?</b>
    </div>

</div>

</body>
</html>
""",
                selectedClient.getName(),
                selectedClient.getCpf(),
                productsHtml.toString(),
                totalGrossValue,
                absoluteDiscount,
                percentageDiscount,
                netValue,
                numberOfInstallments,
                installmentValue,
                sdf.format(java.sql.Date.valueOf(allBillingDates.get(0))),
                sdf.format(java.sql.Date.valueOf(allBillingDates.get(allBillingDates.size() - 1)))
        );

        int choice = JOptionPane.showConfirmDialog(
                null,
                msg,
                "Finalizar Venda",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );

        switch (choice) {
            case JOptionPane.YES_OPTION -> {
                Sale newSale = new Sale(
                        selectedClient.getId(),
                        selectedClient.getName(),
                        productsSold,
                        netValue,
                        numberOfInstallments,
                        firstDueDate
                );

                try {
                    Json.saveItemByType(newSale, 2);
                } catch (IOException ex) {
                    Logger.getLogger(SaleFunctions.class.getName()).log(Level.SEVERE, null, ex);
                }

                return true;
            }
            case JOptionPane.NO_OPTION, JOptionPane.CLOSED_OPTION -> {
                return false;
            }
            default ->
                throw new IllegalStateException("Opção desconhecida: " + choice);
        }
    }

    public static List<Date> returnAllBillingDates(Date billingDate, double numberOfInstallments) {
        List<Date> dates = new ArrayList<>();
        Calendar base = Calendar.getInstance();
        base.setTime(billingDate);

        dates.add(base.getTime());

        for (int i = 1; i < numberOfInstallments; i++) {
            Calendar cal = (Calendar) base.clone();
            cal.add(Calendar.MONTH, i);
            dates.add(cal.getTime());
        }

        return dates;
    }

    public static void addItemOnSaleSummary(JTable tableSaleSummary, String itemId, int itemAmount,
            BigDecimal itemTotal) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DefaultTableModel saleSummaryTableModel = (DefaultTableModel) tableSaleSummary.getModel();

        List<Product> products = mapper.readValue(new File(String.valueOf(getProductsFileLocation())), new TypeReference<List<Product>>() {
        });

        for (Product product : products) {

            if (product.getId().equals(itemId)) {
                Object[] productA = {
                    product.getId(),
                    product.getName(),
                    itemAmount,
                    itemTotal
                };
                saleSummaryTableModel.addRow(productA);
            }
        }
    }
}
