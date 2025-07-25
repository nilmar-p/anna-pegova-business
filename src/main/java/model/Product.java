package model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

import utils.Json;

public class Product {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("volume")
    private int volume;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("price")
    private double price;

    @JsonProperty("margin")
    private int margin;

    @JsonProperty("total")
    private double total;

    @JsonProperty("profit")
    private double profit;

    @JsonCreator
    public Product(@JsonProperty("name") String name, @JsonProperty("volume") int volume,
            @JsonProperty("price") double price, @JsonProperty("amount") int amount,
            @JsonProperty("margin") int margin) {
        this.setId(generateUniqueClientId());
        this.name = name.trim().isEmpty() ? "NOME VAZIO" : name.trim().toUpperCase();
        this.volume = volume < 1 ? 1 : volume;
        this.amount = amount < 0 ? 0 : amount;
        this.price = price < 1 ? 1 : price;
        this.setMargin(margin);
    }

    //
    private int generateUniqueClientId() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            Path fileLocation = Json.getProductsFileLocation();
            Files.createDirectories(fileLocation.getParent());

            List<Product> products = new ArrayList<>();

            int newId;
            boolean idExists;

            do {
                newId = ThreadLocalRandom.current().nextInt(10000, 99999);
                idExists = false;

                for (Product product : products) {
                    if (product.getId() == newId) {
                        idExists = true;
                        break;
                    }
                }
            } while (idExists);

            return newId;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na criação de ID!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return ThreadLocalRandom.current().nextInt(1000, 10000);
        }
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    public double getPrice() {
        return price;
    }

    public int getMargin() {
        return margin;
    }

    public int getAmount() {
        return amount;
    }

    public double getProfit() {
        return profit;
    }

    public double getTotal() {
        return total;
    }

    //
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMargin(int margin) {
        this.margin = margin < 0 ? 1 : margin;
        this.setTotal();
        this.setProfit();
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTotal() {
        this.total = this.price * this.amount;
    }

    public void setProfit() {
        this.profit = this.total * ((double) this.margin / 100);
    }

    @Override
    public String toString() {
        return String.format("Item{name='%s', volume=%d, price=%.2f, margin=%d%%}", name, volume, price, margin);
    }
}
