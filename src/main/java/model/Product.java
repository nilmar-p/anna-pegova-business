package model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

import utils.Json;

public class Product {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("volume")
    private int volume;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("margin")
    private BigDecimal margin;

    @JsonProperty("total")
    private BigDecimal total;

    @JsonProperty("profit")
    private BigDecimal profit;

    // desserialization
    @JsonCreator
    public Product(@JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("volume") int volume,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("amount") int amount,
            @JsonProperty("margin") BigDecimal margin) {
        this.id = id;
        this.name = (name == null || name.trim().isEmpty()) ? "NOME VAZIO" : name.trim().toUpperCase();
        this.volume = Math.max(volume, 1);
        this.amount = Math.max(amount, 0);
        this.price = price.compareTo(BigDecimal.ONE) < 0 ? BigDecimal.ONE : price;
        this.margin = margin.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ONE : margin;
        calculateTotal();
        calculateProfit();
    }

    // new product
    public Product(String name, int volume, BigDecimal price, int amount, BigDecimal margin) {
        this.id = generateUniqueProductId();
        this.name = (name == null || name.trim().isEmpty()) ? "NOME VAZIO" : name.trim().toUpperCase();
        this.volume = Math.max(volume, 1);
        this.amount = Math.max(amount, 0);
        this.price = price.compareTo(BigDecimal.ONE) < 0 ? BigDecimal.ONE : price;
        this.margin = margin.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ONE : margin;
        calculateTotal();
        calculateProfit();
    }

    private String generateUniqueProductId() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Path fileLocation = Json.getProductsFileLocation();
            Files.createDirectories(fileLocation.getParent());

            List<Product> products = new ArrayList<>();
            if (Files.exists(fileLocation) && Files.size(fileLocation) > 0) {
                products = mapper.readValue(fileLocation.toFile(), new TypeReference<List<Product>>() {
                });
            }

            Set<String> existingIds = products.stream()
                    .map(Product::getId)
                    .collect(Collectors.toSet());

            String newId;
            do {
                newId = UUID.randomUUID().toString().substring(0, 8);
            } while (existingIds.contains(newId));

            return newId;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar ID do produto!\n" + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return UUID.randomUUID().toString().substring(0, 8);
        }
    }

    // getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getMargin() {
        return margin;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public BigDecimal getTotal() {
        return total;
    }

    // setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        calculateTotal();
        calculateProfit();
    }

    public void setMargin(BigDecimal margin) {
        this.margin = margin.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ONE : margin;
        calculateProfit();
    }

    public void setAmount(int amount) {
        this.amount = amount;
        calculateTotal();
        calculateProfit();
    }

    private void calculateTotal() {
        this.total = price.multiply(BigDecimal.valueOf(amount)).setScale(2, RoundingMode.HALF_UP);
    }

    private void calculateProfit() {
        this.profit = total.multiply(margin).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return String.format("Product{name='%s', volume=%d, price=%.2f, margin=%s%%}", name, volume, price, margin);
    }
}
