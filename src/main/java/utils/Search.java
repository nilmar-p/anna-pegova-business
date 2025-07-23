package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Client;
import model.Product;
import model.Sale;
import static utils.Json.getProductsFileLocation;
import static utils.Json.getClientsFileLocation;
import static utils.Json.getSalesFileLocation;
import static utils.Json.getCompletedSalesFileLocation;

public class Search {

    public static void searchItemOnTable(JTable tableRegistereds, String search, int type) throws IOException {
        search = search.trim().toUpperCase();

        if (search.isEmpty()) {
            try {
                Json.refreshProductsTable(tableRegistereds);
            } catch (IOException ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        DefaultTableModel tableRegisteredsModel = (DefaultTableModel) tableRegistereds.getModel();
        ObjectMapper mapper = new ObjectMapper();

        tableRegisteredsModel.setRowCount(0);

        switch (type) {
            case 0 -> {
                List<Product> products = mapper.readValue(new File(String.valueOf(getProductsFileLocation())), new TypeReference<>() {
                });

                for (Product product : products) {
                    if (product.getName().startsWith(search)) {
                        Object[] productA = {
                            product.getId(),
                            product.getName(),
                            product.getVolume() + "ml",
                            product.getAmount(),
                            String.format("R$ %.2f", product.getTotal()),
                            String.format("%d%%", product.getMargin()),
                            String.format("R$ %.2f", product.getProfit())
                        };

                        tableRegisteredsModel.addRow(productA);
                    }

                }

                break;
            }
            case 2 -> {
                List<Sale> sales = mapper.readValue(new File(String.valueOf(getSalesFileLocation())), new TypeReference<>() {
                });

                for (Sale sale : sales) {
                    if (sale.getClientName().startsWith(search)) {
                        Object[] saleA = {
                            sale.getId(),
//                            String.format("%d - %s", sale.getProductId(), sale.getProductName()),
                            String.format("%d - %s", sale.getClientId(), sale.getClientName()),
                            Mask.sdf.format(sale.getNextBillingDate())
                        };

                        tableRegisteredsModel.addRow(saleA);
                    }

                }

                break;
            }

            case 3 -> {
                List<Sale> completedSales = mapper.readValue(new File(String.valueOf(getCompletedSalesFileLocation())), new TypeReference<>() {
                });

                for (Sale sale : completedSales) {
                    if (sale.getClientName().startsWith(search)) {
                        Object[] saleA = {
                            sale.getId(),
//                            String.format("%d - %s", sale.getProductId(), sale.getProductName()),
//                            String.format("%d - %s", sale.getClientId(), sale.getClientName()),
                            Mask.sdf.format(sale.getNextBillingDate())
                        };

                        tableRegisteredsModel.addRow(saleA);
                    }

                }

                break;
            }
            default ->
                throw new AssertionError();
        }

        if (tableRegistereds.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum resultado para a pesquisa!",
                    "Atenção!", JOptionPane.WARNING_MESSAGE);
        }

    }

    public static void searchClientsOnTable(JTable tableRegistereds, String search, String filter) throws IOException {
        if (search.isEmpty()) {
            try {
                Json.refreshClientsTable(tableRegistereds);
            } catch (IOException ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        DefaultTableModel tableRegisteredsModel = (DefaultTableModel) tableRegistereds.getModel();
        ObjectMapper mapper = new ObjectMapper();

        List<Client> clients = mapper.readValue(new File(String.valueOf(Json.getClientsFileLocation())), new TypeReference<List<Client>>() {
        });

        tableRegisteredsModel.setRowCount(0);

        switch (filter) {
            case "NOME":
                for (Client client : clients) {
                    if (client.getName().startsWith(search)) {
                        Object[] productA = {
                            client.getId(),
                            client.getName(),
                            client.getPhone(),
                            client.getCpf(),
                            client.getCity()
                        };

                        tableRegisteredsModel.addRow(productA);
                    }

                }
                break;
            case "CIDADE":
                for (Client client : clients) {
                    if (client.getCity().startsWith(search)) {
                        Object[] productA = {
                            client.getId(),
                            client.getName(),
                            client.getPhone(),
                            client.getCpf(),
                            client.getCity()
                        };

                        tableRegisteredsModel.addRow(productA);
                    }

                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Erro na pesquisa!", "ERRO!", JOptionPane.ERROR_MESSAGE);
                throw new AssertionError();
        }

        if (tableRegistereds.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum resultado para a pesquisa!",
                    "Atenção!", JOptionPane.WARNING_MESSAGE);
        }

    }
}
