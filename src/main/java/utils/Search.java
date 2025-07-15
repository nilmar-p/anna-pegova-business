package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Client;
import model.Item;

public class Search {

    public static void searchItemsOnTable(JTable tableRegistereds, String search) throws IOException {
        if (search.isEmpty()) {
            try {
                Json.refreshProductsTable(tableRegistereds);
            } catch (IOException ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        DefaultTableModel tableRegisteredsModel = (DefaultTableModel) tableRegistereds.getModel();
        ObjectMapper mapper = new ObjectMapper();

        List<Item> products = mapper.readValue(new File(String.valueOf(Json.getProductsFileLocation())), new TypeReference<List<Item>>() {
        });

        tableRegisteredsModel.setRowCount(0);

        for (Item product : products) {
            if (product.getName().startsWith(search)) {
                Object[] productA = {
                    product.getId(),
                    product.getName(),
                    product.getVolume() + "ml",
                    product.getAmount(),
                    String.format("R$ %.2f", product.getTotal()),
                    String.format("%.2f%%", product.getMargin()),
                    String.format("R$ %.2f", product.getProfit())
                };

                tableRegisteredsModel.addRow(productA);
            }

        }
    }

    public static void searchClientsOnTable(JTable tableRegistereds, String search) throws IOException {
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
    }
}
