package utils;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

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
}
