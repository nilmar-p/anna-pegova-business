package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Item;

public class Json {

    //getter
    public static Path getProductsFileLocation() {
        return Paths.get("C:\\anna-pegova-estoque\\products.json");
    }

    //methods
    public static void saveProduct(Item newItem) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Files.createDirectories(getProductsFileLocation().getParent());

        List<Item> items = new ArrayList<>();

        if (Files.exists(getProductsFileLocation())) {
            try {
                String content = Files.readString(getProductsFileLocation());

                if (!content.isEmpty()) {
                    items = mapper.readValue(content,
                            new TypeReference<List<Item>>() {
                    });
                }
            } catch (Exception e) {
                System.err.println("Erro ao ler arquivo existente: " + e.getMessage());
            }
        }
        items.add(newItem);

        String json = mapper.writeValueAsString(items);

        Files.write(getProductsFileLocation(), json.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE);
    }

    public static void refreshTable(JTable registeredsProducts) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DefaultTableModel clientsTableModel = (DefaultTableModel) registeredsProducts.getModel();

        clientsTableModel.setRowCount(0);

        List<Item> products = mapper.readValue(new File(String.valueOf(getProductsFileLocation())), new TypeReference<List<Item>>() {
        });

        for (Item product : products) {
            Object[] productA = {
                product.getId(),
                product.getName(),
                product.getVolume() + "ml",
                product.getAmount(),
                String.format("R$ %.2f", product.getTotal()),
                String.format("%.2f%%", product.getMargin()),
                String.format("R$ %.2f", product.getProfit())
            };
            clientsTableModel.addRow(productA);

        }
    }

    public static void deleteItemFromJson(JTable tableRegistereds) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Files.createDirectories(getProductsFileLocation().getParent());

        int selectedRow = tableRegistereds.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Você deve selecionar um item da lista!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String content = Files.exists(getProductsFileLocation()) ? Files.readString(getProductsFileLocation()) : "[]";
        List<Item> products = mapper.readValue(content, new TypeReference<>() {
        });

        String nomeItem = tableRegistereds.getValueAt(selectedRow, 1).toString();
        int choice = JOptionPane.showConfirmDialog(
                null,
                String.format("Deseja prosseguir com o apagamento de %s?", nomeItem),
                "Confirmação",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(tableRegistereds.getValueAt(selectedRow, 0).toString());

            products.removeIf(item -> item.getId() == id);

            mapper.writeValue(getProductsFileLocation().toFile(), products);

            refreshTable(tableRegistereds);
        }
    }

}
