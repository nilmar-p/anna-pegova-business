package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
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
    @JsonProperty("netValue")
    private final double netValue;
    @JsonProperty("installmentValue")
    private final double installmentValue;
    @JsonProperty("actualInstallment")
    private int actualInstallment = 0;
    @JsonProperty("allBillingDates")
    private List<Date> allBillingDates;
    @JsonProperty("nextBillingDate")
    private Date nextBillingDate;
    @JsonProperty("completed")
    public boolean isCompleted = false;

    public Sale(@JsonProperty("clientId") int clientId, @JsonProperty("clientName") String clientName,
            @JsonProperty("netValue") double netValue, @JsonProperty("installmentValue") double installmentValue,
            @JsonProperty("productsSold") List<ProductSold> productsSold, @JsonProperty("allBillingDates") List<Date> allBillingDates) {
        this.id = generateUniqueClientId();
        this.clientId = clientId;
        this.clientName = clientName;

        this.productsSold = productsSold;

        this.netValue = netValue;
        this.installmentValue = installmentValue;
        this.allBillingDates = allBillingDates;
        this.setActualInstallment();
    }

    //
    private int generateUniqueClientId() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            Path fileLocation = Json.getSalesFileLocation();
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
            System.out.println("ERRO NA CRIACAO DE ID");
            return ThreadLocalRandom.current().nextInt(1000, 10000);
        }
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public double getNetValue() {
        return netValue;
    }

    public double getInstallmentValue() {
        return installmentValue;
    }

    public int getActualInstallment() {
        return actualInstallment;
    }

    public List<Date> getAllBillingDates() {
        return allBillingDates;
    }

    public Date getNextBillingDate() {
        return nextBillingDate;
    }

    public List<ProductSold> getProductsSold() {
        return productsSold;
    }

    //
    public void setId(int id) {
        this.id = id;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setActualInstallment() {
        this.actualInstallment++;
        setNextBillingDateAs(allBillingDates.get(actualInstallment - 1));
    }

    public void setAllBillingDates(List<Date> allBillingDates) {
        this.allBillingDates = allBillingDates;
    }

    public void setNextBillingDateAs(Date nextBillingDate) {
        this.nextBillingDate = nextBillingDate;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "Sale{" + "id=" + id + ", clientId=" + clientId + ", clientName=" + clientName + ", productsSold=" + productsSold + ", netValue=" + netValue + ", installmentValue=" + installmentValue + ", actualInstallment=" + actualInstallment + ", allBillingDates=" + allBillingDates + ", nextBillingDate=" + nextBillingDate + ", isCompleted=" + isCompleted + '}';
    }

}
