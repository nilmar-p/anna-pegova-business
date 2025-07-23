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
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Client;
import model.Product;
import model.Sale;

public class Json {

    //getter
    public static Path getProductsFileLocation() {
        return Paths.get("C:\\anna-pegova-estoque\\products.json");
    }

    public static Path getClientsFileLocation() {
        return Paths.get("C:\\anna-pegova-estoque\\clients.json");
    }

    public static Path getSalesFileLocation() {
        return Paths.get("C:\\anna-pegova-estoque\\sales.json");
    }

    public static Path getCompletedSalesFileLocation() {
        return Paths.get("C:\\anna-pegova-estoque\\completed-sales.json");
    }

    //methods
    public static void saveProduct(Product newItem) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Files.createDirectories(getProductsFileLocation().getParent());

        List<Product> items = new ArrayList<>();

        if (Files.exists(getProductsFileLocation())) {
            try {
                String content = Files.readString(getProductsFileLocation());

                if (!content.isEmpty()) {
                    items = mapper.readValue(content,
                            new TypeReference<List<Product>>() {
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

    public static void saveSale(Sale newSale) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Files.createDirectories(getSalesFileLocation().getParent());
        Files.createDirectories(getCompletedSalesFileLocation().getParent());

        if (Files.notExists(getCompletedSalesFileLocation())) {
            Files.write(getCompletedSalesFileLocation(),
                    "[]".getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE);
        }

        List<Sale> sales = new ArrayList<>();

        if (Files.exists(getSalesFileLocation())) {
            try {
                String content = Files.readString(getSalesFileLocation());

                if (!content.isBlank()) {
                    sales = mapper.readValue(content, new TypeReference<>() {
                    });
                }
            } catch (Exception e) {
                System.err.println("Erro ao ler arquivo existente: " + e.getMessage());
            }
        }

        sales.add(newSale);

        String json = mapper.writeValueAsString(sales);

        Files.write(getSalesFileLocation(), json.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE);
    }

    public static void refreshProductsTable(JTable productsTable) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DefaultTableModel clientsTableModel = (DefaultTableModel) productsTable.getModel();

        clientsTableModel.setRowCount(0);

        List<Product> products = mapper.readValue(new File(String.valueOf(getProductsFileLocation())), new TypeReference<List<Product>>() {
        });

        for (Product product : products) {
            Object[] productA = {
                product.getId(),
                product.getName(),
                product.getVolume() + "ml",
                product.getAmount(),
                String.format("R$ %.2f", product.getTotal()),
                String.format("%d%%", product.getMargin()),
                String.format("R$ %.2f", product.getProfit())
            };
            clientsTableModel.addRow(productA);

        }
    }

    public static void refreshSummariesTables(JTable currentSalesTable, JTable completedSalesTable) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DefaultTableModel currentSalesTableModel = (DefaultTableModel) currentSalesTable.getModel();
        DefaultTableModel completedSalesTableModel = (DefaultTableModel) completedSalesTable.getModel();

        currentSalesTableModel.setRowCount(0);
        completedSalesTableModel.setRowCount(0);

        List<Sale> sales = mapper.readValue(new File(String.valueOf(getSalesFileLocation())), new TypeReference<List<Sale>>() {
        });

        List<Sale> completedSales = mapper.readValue(new File(String.valueOf(getCompletedSalesFileLocation())), new TypeReference<List<Sale>>() {
        });

        for (Sale sale : sales) {

            Object[] saleA = {
                sale.getId(),
                String.format("R$ %.2f", sale.getNetValue()),
                String.format("%d - %s", sale.getClientId(), sale.getClientName()),
                Mask.sdf.format(sale.getNextBillingDate())
            };
            currentSalesTableModel.addRow(saleA);

        }

        for (Sale completedSale : completedSales) {

            Object[] saleA = {
                completedSale.getId(),
                completedSale.getId(),
                completedSale.getNetValue(),
                String.format("%d - %s", completedSale.getClientId(), completedSale.getClientName()),
                Mask.sdf.format(completedSale.getNextBillingDate())
            };
            completedSalesTableModel.addRow(saleA);
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

    public static Object returnRowAsObject(int itemId, int type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        switch (type) {
            case 0 -> {
                List<Product> products = mapper.readValue(new File(String.valueOf(getProductsFileLocation())), new TypeReference<>() {
                });

                Product product = null;
                for (Product checkedProduct : products) {
                    if (checkedProduct.getId() == itemId) {
                        product = checkedProduct;
                        break;
                    }
                }

                return product;
            }
            case 1 -> {
                List<Client> clients = mapper.readValue(new File(String.valueOf(getClientsFileLocation())), new TypeReference<>() {
                });

                Client client = null;
                for (Client checkedClient : clients) {
                    if (checkedClient.getId() == itemId) {
                        client = checkedClient;
                        break;
                    }
                }
                return client;
            }
            case 2 -> {
                List<Sale> sales = mapper.readValue(new File(String.valueOf(getSalesFileLocation())), new TypeReference<>() {
                });

                Sale sale = null;
                for (Sale checkedSale : sales) {
                    if (checkedSale.getId() == itemId) {
                        sale = checkedSale;
                        break;
                    }
                }

                return sale;

            }
            case 3 -> {
                List<Sale> completedSales = mapper.readValue(new File(String.valueOf(getCompletedSalesFileLocation())), new TypeReference<>() {
                });

                Sale sale = null;
                for (Sale checkedCompletedSale : completedSales) {
                    if (checkedCompletedSale.getId() == itemId) {
                        sale = checkedCompletedSale;
                        break;
                    }
                }

                return sale;

            }
            default ->
                throw new AssertionError();
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
                List<Product> products = mapper.readValue(content, new TypeReference<>() {
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

            case 2 -> {
                List<Sale> sales = mapper.readValue(content, new TypeReference<>() {
                });
                sales.removeIf(sale -> sale.getId() == id);
                mapper.writeValue(fileLocation.toFile(), sales);
                refreshSummariesTables(tableRegistereds, tableRegistereds);
            }

            default ->
                throw new AssertionError("Tipo não suportado: " + type);
        }
    }

    public static void editIndexFromJson(int idEditedItem, Object editedItem, int type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        switch (type) {
            case 0 -> {
                List<Product> products = mapper.readValue(new File(String.valueOf(getProductsFileLocation())), new TypeReference<>() {
                });

                Product editedProduct = (Product) editedItem;

                for (Product product : products) {
                    if (product.getId() == idEditedItem) {

                        product.setId(idEditedItem);

                        product.setName(editedProduct.getName());
                        product.setVolume(editedProduct.getVolume());
                        product.setPrice(editedProduct.getPrice());
                        product.setAmount(editedProduct.getAmount());
                        product.setMargin(editedProduct.getMargin());

                        break;
                    }
                }
                String json = mapper.writeValueAsString(products);

                Files.write(getProductsFileLocation(), json.getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING,
                        StandardOpenOption.WRITE);
                break;
            }
            case 1 -> {
                List<Client> clients = mapper.readValue(new File(String.valueOf(getClientsFileLocation())), new TypeReference<>() {
                });

                Client editedClient = (Client) editedItem;

                for (Client client : clients) {
                    if (client.getId() == idEditedItem) {

                        client.setId(idEditedItem);

                        client.setName(editedClient.getName());
                        client.setPhone(editedClient.getPhone());
                        client.setCpf(editedClient.getCpf());
                        client.setCity(editedClient.getCity());
                        client.setStreet(editedClient.getStreet());
                        client.setHouseNumber(editedClient.getHouseNumber());
                        client.setNeighborhood(editedClient.getNeighborhood());
                        client.setCep(editedClient.getCep());

                        break;
                    }
                }
                String json = mapper.writeValueAsString(clients);

                Files.write(getClientsFileLocation(), json.getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING,
                        StandardOpenOption.WRITE);
                break;
            }

            case 2 -> {
                // Lê as listas do arquivo
                List<Sale> sales = mapper.readValue(new File(String.valueOf(getSalesFileLocation())), new TypeReference<>() {
                });
                List<Sale> completedSales = mapper.readValue(new File(String.valueOf(getCompletedSalesFileLocation())), new TypeReference<>() {
                });

                Iterator<Sale> iterator = sales.iterator();

                while (iterator.hasNext()) {
                    Sale sale = iterator.next();

                    if (sale.getId() == idEditedItem) {
                        if (sale.getActualInstallment() < sale.getAllBillingDates().size()) {
                            sale.setActualInstallment();
                        } else {
                            System.out.println("Venda concluída, movendo para completedSales");
                            sale.setCompleted(true);

                            iterator.remove();

                            completedSales.add(sale);

                            String completedJson = mapper.writeValueAsString(completedSales);
                            Files.write(getCompletedSalesFileLocation(), completedJson.getBytes(StandardCharsets.UTF_8),
                                    StandardOpenOption.CREATE,
                                    StandardOpenOption.TRUNCATE_EXISTING,
                                    StandardOpenOption.WRITE);
                        }
                        break;
                    }
                }

                // Salva a lista de vendas em andamento atualizada
                String salesJson = mapper.writeValueAsString(sales);
                Files.write(getSalesFileLocation(), salesJson.getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING,
                        StandardOpenOption.WRITE);
                break;
            }

            default ->
                throw new AssertionError();
        }
    }
}
