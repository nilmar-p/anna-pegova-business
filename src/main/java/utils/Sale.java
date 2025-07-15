package utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.Client;
import model.Item;

public class Sale {

    public static boolean finishSale(
            String clientName,
            String clientCpf,
            double unitPrice,
            double grossValue,
            int amount,
            double absoluteDiscount,
            double percentageDiscount,
            double netValue,
            double installmentValue,
            Item selectedProduct,
            Client selectedClient,
            String billingDate,
            int numberOfInstallments,
            String lastBillingDate) {

        String msg = String.format(
                """
    <html>
    <body style='font-family:"Segoe UI Semibold", sans-serif; font-size:13px; color:#333;'>

    <div style='width: 370px; margin: 0 auto; background-color: #f8f9fa; border: 1px solid #dee2e6; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);'>

        <div style='background-color: #0EABAA; color: #ffffff; padding: 10px 15px; border-radius: 8px 8px 0 0; text-align:center;'>
            <b style='font-size:18px;'>Resumo da Compra</b>
        </div>

        <div style='padding: 5px 15px 12px 15px;'>
            <table style='width:100%%; border-collapse:collapse;' cellspacing="0" cellpadding="0">
                
                <tr style='border-bottom: 1px solid #e9ecef;'>
                    <td style='padding:10px 0;'>
                        <div style='text-align:center; font-size:12px; color:#495057;'>
                            Cliente: <b style='color:#212529;'>%s</b> &nbsp;&bull;&nbsp; CPF: <b style='color:#212529;'>%s</b>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td style='padding:10px 0 4px 0;'>
                        <span style='color:#6c757d;'>Valor Unitário:</span>
                        <span style='float:right; color:#212529; font-weight:bold;'>R$ %.2f</span>
                    </td>
                </tr>
                <tr style='border-bottom: 1px solid #e9ecef;'>
                    <td style='padding:4px 0 10px 0;'>
                        <span style='color:#6c757d;'>Quantidade:</span>
                        <span style='float:right; color:#212529; font-weight:bold;'>%d</span>
                    </td>
                </tr>
                <tr>
                    <td style='padding:10px 0 4px 0;'>
                        <span style='color:#6c757d;'>Valor Bruto:</span>
                        <span style='float:right; color:#212529;'>R$ %.2f</span>
                    </td>
                </tr>
                <tr style='border-bottom: 1px solid #e9ecef;'>
                    <td style='padding:4px 0 10px 0;'>
                        <span style='color:#6c757d;'>Desconto:</span>
                        <span style='float:right; color:#dc3545; font-weight:bold;'>- R$ %.2f (%.0f%%)</span>
                    </td>
                </tr>

                <tr>
                    <td style='text-align:center; padding: 12px 0;'>
                        <span style='font-size:12px; color:#6c757d;'>VALOR TOTAL A PAGAR</span><br>
                        <span style='font-size:22px; color:#28a745; font-weight:bold; line-height:1.1;'>R$ %.2f</span>
                    </td>
                </tr>
                
                <tr>
                    <td style='background-color: #f0f4f8; padding: 10px; border-radius: 6px; text-align:center;'>
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
                // Mantenha a mesma ordem de argumentos que no seu código original
                clientName,
                clientCpf,
                unitPrice,
                amount,
                grossValue,
                absoluteDiscount,
                percentageDiscount,
                netValue,
                numberOfInstallments,
                installmentValue,
                billingDate,
                lastBillingDate
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
                return true;
            }
            case JOptionPane.NO_OPTION -> {
                return false;
            }
            case JOptionPane.CLOSED_OPTION -> {
                System.out.println("Janela fechada sem confirmação.");
                return false;
            }
            default ->
                throw new IllegalStateException("Opção desconhecida: " + choice);
        }
    }

    public static List<Date> allBillingDates(Date billingDate, int numberOfInstallments) {
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

}
