package model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import utils.Json;

public class Item {

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
    private double margin;

    @JsonProperty("total")
    private double total;

    @JsonProperty("profit")
    private double profit;

    @JsonCreator
    public Item(@JsonProperty("name") String name, @JsonProperty("volume") int volume,
            @JsonProperty("price") double price, @JsonProperty("amount") int amount,
            @JsonProperty("margin") double margin) {
        this.id = generateUniqueClientId();
        this.name = name.trim().isEmpty() ? "NOME VAZIO" : name.trim().toUpperCase();
        this.volume = volume < 1 ? 1 : volume;
        this.amount = amount < 1 ? 1 : amount;
        this.price = price < 1 ? 1 : price;
        this.margin = margin < 0 ? 1 : margin;
        this.setTotal();
        this.setProfit();
    }

    //
    private int generateUniqueClientId() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            Path fileLocation = Json.getProductsFileLocation();
            Files.createDirectories(fileLocation.getParent());

            List<Item> products = new ArrayList<>();

            int newId;
            boolean idExists;

            do {
                newId = ThreadLocalRandom.current().nextInt(10000, 99999);
                idExists = false;

                for (Item product : products) {
                    if (product.getId() == newId) {
                        idExists = true;
                        break;
                    }
                }
            } while (idExists);

            return newId;

        } catch (Exception e) {
            System.out.println("ERRO NA CRIACAO DE ID");
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

    public double getMargin() {
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
        this.margin = margin;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTotal() {
        this.total = this.price * this.amount;
    }

    public void setProfit() {
        this.profit = this.total * (this.margin / 100);
    }

    @Override
    public String toString() {
        return String.format(
                "Item {\n"
                + "  Name   : %s\n"
                + "  Volume : %.2f\n"
                + "  Price  : %.2f\n"
                + "  Margin : %.2f%%\n"
                + "}", name, volume, price, margin);
    }
}
