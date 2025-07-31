package utils;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import model.Client;
import model.Product;

public class Mask {

    //
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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

        if ((double) spinnerPrice.getValue() <= 0.0) {
            JOptionPane.showMessageDialog(null, "Preço não pode ser 0!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ((int) spinnerMargin.getValue() <= 0) {
            JOptionPane.showMessageDialog(null, "Margem de lucro não pode ser 0!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static void clearSearchFieldsSummaries(JTextField searchCurrent, JTextField searchCompleted) {
        searchCurrent.setText("");
        searchCompleted.setText("");
    }

    public static void clearSearchField(JTextField searchField) {
        searchField.setText("");
    }

    public static void refreshSaleComboBox(JComboBox<String> comboBox, List<?> filteredItems, int type) {
        comboBox.removeAllItems();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        switch (type) {
            case 0 -> {
                for (Object obj : filteredItems) {
                    if (obj instanceof Product product && product.getAmount() > 0) {
                        model.addElement(String.format("%s - %s", product.getId(), product.getName()));
                    }
                }
            }

            case 1 -> {
                for (Object obj : filteredItems) {
                    if (obj instanceof Client client) {
                        model.addElement(String.format("%s - %s", client.getId(), client.getName()));
                    }
                }
            }

            default ->
                throw new IllegalArgumentException("Tipo inválido: " + type);
        }

        comboBox.setModel(model);
        comboBox.setSelectedItem(null);
    }

}
