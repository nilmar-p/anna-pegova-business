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
import model.Client;
import model.Item;

public class Json {

    //getter
    public static Path getProductsFileLocation() {
        return Paths.get("C:\\anna-pegova-estoque\\products.json");
    }

    public static Path getClientsFileLocation() {
        return Paths.get("C:\\anna-pegova-estoque\\clients.json");
    }

    public static Path getSalesInProgressFileLocation() {
        return Paths.get("C:\\anna-pegova-estoque\\sales-in-progress.json");
    }

    public static Path getHistoryFileLocation() {
        return Paths.get("C:\\anna-pegova-estoque\\history.json");
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

    public static void saveClient(Client newClient) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Files.createDirectories(getClientsFileLocation().getParent());

        List<Client> clients = new ArrayList<>();

        if (Files.exists(getClientsFileLocation())) {
            try {
                String content = Files.readString(getClientsFileLocation());

                if (!content.isEmpty()) {
                    clients = mapper.readValue(content,
                            new TypeReference<List<Client>>() {
                    });
                }
            } catch (Exception e) {
                System.err.println("Erro ao ler arquivo existente: " + e.getMessage());
            }
        }
        clients.add(newClient);

        String json = mapper.writeValueAsString(clients);

        Files.write(getClientsFileLocation(), json.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE);
    }

    public static void refreshProductsTable(JTable productsTable) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DefaultTableModel clientsTableModel = (DefaultTableModel) productsTable.getModel();

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

    public static void refreshClientsTable(JTable clientsTable) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DefaultTableModel clientsTableModel = (DefaultTableModel) clientsTable.getModel();

        clientsTableModel.setRowCount(0);

        List<Client> clients = mapper.readValue(new File(String.valueOf(getClientsFileLocation())), new TypeReference<List<Client>>() {
        });

        for (Client client : clients) {
            Object[] clientA = {
                client.getId(),
                client.getName(),
                client.getPhone(),
                client.getCpf(),
                client.getCity()
            };
            clientsTableModel.addRow(clientA);

        }
    }

    public static void deleteIndexFromJson(JTable tableRegistereds, Path fileLocation, int type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Files.createDirectories(fileLocation.getParent());

        int selectedRow = tableRegistereds.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Você deve selecionar um item da lista!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String content = Files.exists(fileLocation) ? Files.readString(fileLocation) : "[]";

        int id = Integer.parseInt(tableRegistereds.getValueAt(selectedRow, 0).toString());
        String itemName = tableRegistereds.getValueAt(selectedRow, 1).toString();

        int choice = JOptionPane.showConfirmDialog(
                null,
                String.format("Deseja prosseguir com o apagamento de %s?", itemName),
                "Confirmação",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        switch (type) {
            case 0 -> {
                List<Item> products = mapper.readValue(content, new TypeReference<>() {
                });
                products.removeIf(item -> item.getId() == id);
                mapper.writeValue(fileLocation.toFile(), products);
                refreshProductsTable(tableRegistereds);
            }
            case 1 -> {
                List<Client> clients = mapper.readValue(content, new TypeReference<>() {
                });
                clients.removeIf(client -> client.getId() == id);
                mapper.writeValue(fileLocation.toFile(), clients);
                refreshClientsTable(tableRegistereds);
            }
            default ->
                throw new AssertionError("Tipo não suportado: " + type);
        }
    }
}
