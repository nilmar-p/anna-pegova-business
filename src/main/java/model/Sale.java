package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;
import utils.Json;

public class Sale {

    @JsonProperty("id")
    private int id;

    @JsonProperty("clientId")
    private final int clientId;

    @JsonProperty("clientName")
    private String clientName;

    @JsonProperty("productsSold")
    private List<ProductSold> productsSold = new ArrayList<>();

    @JsonProperty("totalValue")
    private final BigDecimal totalValue;

    @JsonProperty("numberOfInstallments")
    private int numberOfInstallments;

    @JsonProperty("installments")
    private List<Installment> installments;

    @JsonProperty("completed")
    private boolean completed = false;

    @JsonProperty("firstDueDate")
    private LocalDate firstDueDate;

    // desserialization
    @JsonCreator
    public Sale(
            @JsonProperty("id") int id,
            @JsonProperty("clientId") int clientId,
            @JsonProperty("clientName") String clientName,
            @JsonProperty("productsSold") List<ProductSold> productsSold,
            @JsonProperty("totalValue") BigDecimal totalValue,
            @JsonProperty("numberOfInstallments") int numberOfInstallments,
            @JsonProperty("firstDueDate") LocalDate firstDueDate) {

        this.id = id;
        this.clientId = clientId;
        this.clientName = clientName;
        this.productsSold = productsSold != null ? productsSold : new ArrayList<>();
        this.totalValue = totalValue;
        this.numberOfInstallments = numberOfInstallments;
        if (firstDueDate != null && numberOfInstallments > 0) {
            this.firstDueDate = firstDueDate;
            this.installments = generateInstallments(numberOfInstallments, totalValue, firstDueDate);
        } else {
            this.installments = new ArrayList<>();
        }
    }

    // new sale
    public Sale(int clientId, String clientName, List<ProductSold> productsSold,
            BigDecimal totalValue, int numberOfInstallments, LocalDate firstDueDate) {
        this.id = generateUniqueClientId();
        this.clientId = clientId;
        this.clientName = clientName;
        this.productsSold = productsSold != null ? productsSold : new ArrayList<>();
        this.totalValue = totalValue;
        this.numberOfInstallments = numberOfInstallments;
        if (firstDueDate != null && numberOfInstallments > 0) {
            this.firstDueDate = firstDueDate;
            System.out.println(this.firstDueDate);
            this.installments = generateInstallments(numberOfInstallments, totalValue, firstDueDate);
        } else {
            this.installments = new ArrayList<>();
        }
    }

    private static int generateUniqueClientId() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            Path fileLocation = Json.getSalesFileLocation();
            Files.createDirectories(fileLocation.getParent());

            List<Sale> sales = new ArrayList<>();
            if (Files.exists(fileLocation)) {
                sales = mapper.readValue(fileLocation.toFile(),
                        mapper.getTypeFactory().constructCollectionType(List.class, Sale.class));
            }

            int newId;
            boolean idExists;

            do {
                newId = ThreadLocalRandom.current().nextInt(10000, 99999);
                idExists = false;

                for (Sale sale : sales) {
                    if (sale.getId() == newId) {
                        idExists = true;
                        break;
                    }
                }
            } while (idExists);

            return newId;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro na criação de ID!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            return ThreadLocalRandom.current().nextInt(1000, 10000);
        }
    }

    public static List<Installment> generateInstallments(int numberOfInstallments, BigDecimal totalValue, LocalDate firstDueDate) {
        List<Installment> installments = new ArrayList<>();

        BigDecimal installmentValue = totalValue.divide(BigDecimal.valueOf(numberOfInstallments), 2, RoundingMode.HALF_UP);
        LocalDate currentDueDate = firstDueDate;

        for (int i = 1; i <= numberOfInstallments; i++) {
            installments.add(new Installment(i, installmentValue, currentDueDate));
            currentDueDate = currentDueDate.plusMonths(1);
        }

        return installments;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public List<ProductSold> getProductsSold() {
        return productsSold;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public int getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDate getFirstDueDate() {
        return firstDueDate;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Sale{"
                + "id=" + id
                + ", clientId=" + clientId
                + ", clientName='" + clientName + '\''
                + ", productsSold=" + productsSold
                + ", totalValue=" + totalValue
                + ", installments=" + installments
                + '}';
    }
}
