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
import model.ProductSold;
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
    public static void saveItemByType(Object newItem, int type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Path filePath;
        TypeReference<?> typeReference;

        switch (type) {
            case 0 -> {
                filePath = getProductsFileLocation();
                typeReference = new TypeReference<List<Product>>() {};
            }
            case 1 -> {
                filePath = getClientsFileLocation();
                typeReference = new TypeReference<List<Client>>() {};
            }
            case 2 -> {
                filePath = getSalesFileLocation();
                typeReference = new TypeReference<List<Sale>>() {};

                // Cria o diretório do arquivo de vendas concluídas
                Path completedSalesPath = Json.getCompletedSalesFileLocation();
                Files.createDirectories(completedSalesPath.getParent());

                // Cria o arquivo se ele ainda não existir
                if (!Files.exists(completedSalesPath)) {
                    Files.writeString(completedSalesPath, "[]", StandardCharsets.UTF_8,
                            StandardOpenOption.CREATE,
                            StandardOpenOption.WRITE);
                }
            }
            default -> throw new IllegalArgumentException("Tipo inválido: " + type);
        }

        Files.createDirectories(filePath.getParent());

        List<Object> items = new ArrayList<>();

        if (Files.exists(filePath)) {
            try {
                String content = Files.readString(filePath);
                if (!content.isEmpty()) {
                    items = (List<Object>) mapper.readValue(content, typeReference);
                }
            } catch (IOException e) {
                System.err.println("Erro ao ler arquivo existente: " + e.getMessage());
            }
        }

        items.add(newItem);

        String json = mapper.writeValueAsString(items);

        Files.write(filePath, json.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE);
    }


    public static void refreshTableByType(JTable table, int type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        model.setRowCount(0);

        switch (type) {
            case 0 -> {
                List<Product> products = mapper.readValue(
                        new File(String.valueOf(getProductsFileLocation())),
                        new TypeReference<List<Product>>() {
                }
                );

                for (Product product : products) {
                    Object[] row = {
                        product.getId(),
                        product.getName(),
                        product.getVolume() + "ml",
                        product.getAmount(),
                        String.format("R$ %.2f", product.getTotal()),
                        String.format("%d%%", product.getMargin()),
                        String.format("R$ %.2f", product.getProfit())
                    };
                    model.addRow(row);
                }
            }

            case 1 -> {
                List<Client> clients = mapper.readValue(
                        new File(String.valueOf(getClientsFileLocation())),
                        new TypeReference<List<Client>>() {
                }
                );

                for (Client client : clients) {
                    Object[] row = {
                        client.getId(),
                        client.getName(),
                        client.getPhone(),
                        client.getCpf(),
                        client.getCity()
                    };
                    model.addRow(row);
                }
            }

            default ->
                throw new IllegalArgumentException("Tipo inválido: " + type);
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

        for (Sale sale : sales) {

            Object[] saleA = {
                sale.getId(),
                String.format("R$ %.2f", sale.getNetValue()),
                String.format("%d - %s", sale.getClientId(), sale.getClientName()),
                Mask.sdf.format(sale.getNextBillingDate())
            };
            currentSalesTableModel.addRow(saleA);

        }
        
        List<Sale> completedSales = mapper.readValue(new File(String.valueOf(getCompletedSalesFileLocation())), new TypeReference<List<Sale>>() {
        });

        for (Sale completedSale : completedSales) {

            Object[] saleA = {
                completedSale.getId(),
                String.format("R$ %.2f", completedSale.getNetValue()),
                String.format("%d - %s", completedSale.getClientId(), completedSale.getClientName()),
                Mask.sdf.format(completedSale.getNextBillingDate())
            };
            completedSalesTableModel.addRow(saleA);

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
                refreshTableByType(tableRegistereds, 0);

            }

            case 1 -> {
                List<Client> clients = mapper.readValue(content, new TypeReference<>() {
                });
                clients.removeIf(client -> client.getId() == id);
                mapper.writeValue(fileLocation.toFile(), clients);
                refreshTableByType(tableRegistereds, 1);
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

    public static void updateStockFromSale(List<ProductSold> productsSold) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Files.createDirectories(Json.getProductsFileLocation().getParent());
        String content = Files.exists(Json.getProductsFileLocation()) ? Files.readString(Json.getProductsFileLocation()) : "[]";

        List<Product> products = mapper.readValue(content, new TypeReference<>() {
        });

        for (ProductSold productSold : productsSold) {
            for (Product product : products) {
                if (productSold.getId() == product.getId()) {
                    product.setAmount(product.getAmount() - productSold.getQuantity());

                    break;
                }
            }

        }

        mapper.writeValue(Json.getProductsFileLocation().toFile(), products);
    }

    public static void updateStockFromCanceledSale(int saleId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Files.createDirectories(Json.getProductsFileLocation().getParent());
        String contentProducts = Files.exists(Json.getProductsFileLocation()) ? Files.readString(Json.getProductsFileLocation()) : "[]";

        Files.createDirectories(Json.getSalesFileLocation().getParent());
        String contentSales = Files.exists(Json.getSalesFileLocation()) ? Files.readString(Json.getSalesFileLocation()) : "[]";

        List<Product> products = mapper.readValue(contentProducts, new TypeReference<>() {
        });

        List<Sale> sales = mapper.readValue(contentSales, new TypeReference<>() {
        });

        for (Sale sale : sales) {
            if (sale.getId() == saleId) {
                for (int i = 0; i < sale.getProductsSold().size(); i++) {
                    for (Product product : products) {
                        if (product.getId() == sale.getProductsSold().get(i).getId()) {
                            product.setAmount(product.getAmount() + sale.getProductsSold().get(i).getQuantity());
                            break;
                        }
                    }
                }
                break;
            }
        }

        mapper.writeValue(Json.getProductsFileLocation().toFile(), products);
    }
}
