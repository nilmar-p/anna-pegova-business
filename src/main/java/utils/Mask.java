package utils;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import model.Client;
import model.Item;

public class Mask {

    public static boolean isValidForm(JTextField fieldName, JSpinner spinnerVolume, JSpinner spinnerPrice,
            JSpinner spinnerAmount, JSpinner spinnerMargin) {
        if (fieldName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome não pode ser vazio!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ((int) spinnerVolume.getValue() <= 0) {
            JOptionPane.showMessageDialog(null, "Volume não pode ser 0!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ((int) spinnerPrice.getValue() <= 0) {
            JOptionPane.showMessageDialog(null, "Preço não pode ser 0!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ((int) spinnerAmount.getValue() <= 0) {
            JOptionPane.showMessageDialog(null, "Quantidade não pode ser 0!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ((int) spinnerMargin.getValue() <= 0) {
            JOptionPane.showMessageDialog(null, "Margem de lucro não pode ser 0!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static void refreshDiscountSpinner(
            JComboBox<?> comboDiscount,
            JComboBox<String> comboProduct,
            JSpinner spinnerAmount,
            JSpinner spinnerDiscountValue,
            JLabel labelDiscountValue,
            List<Item> productsList
    ) {
        SpinnerNumberModel defaultModel = new SpinnerNumberModel(1, 1, null, 1);
        spinnerDiscountValue.setModel(defaultModel);

        switch (comboDiscount.getSelectedIndex()) {
            case 1 -> {
                labelDiscountValue.setEnabled(true);
                spinnerDiscountValue.setEnabled(true);

                SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 100, 1);
                spinnerDiscountValue.setModel(model);
            }
            case 2 -> {
                labelDiscountValue.setEnabled(true);
                spinnerDiscountValue.setEnabled(true);

                int selected = comboProduct.getSelectedIndex();
                if (selected < 0 || selected >= productsList.size()) {
                    JOptionPane.showMessageDialog(null, "Selecione um produto válido antes de aplicar o desconto!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    spinnerDiscountValue.setModel(defaultModel);
                    return;
                }

                double maxDiscount = productsList.get(selected).getPrice() * ((Number) spinnerAmount.getValue()).intValue();

                if (maxDiscount != productsList.get(selected).getTotal()) {
                    JOptionPane.showMessageDialog(null, "Desconto maior que o total!", "ERRO!", JOptionPane.ERROR_MESSAGE);
                    spinnerDiscountValue.setModel(defaultModel);
                    return;
                }

                SpinnerNumberModel model = new SpinnerNumberModel(1, 1, maxDiscount, 1);
                spinnerDiscountValue.setModel(model);
            }
            default -> {
                SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 0, 0);
                spinnerDiscountValue.setModel(model);
                labelDiscountValue.setEnabled(false);
                spinnerDiscountValue.setEnabled(false);
            }
        }
    }
}
