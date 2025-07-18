package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import utils.Json;

public class Client {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("city")
    private String city;

    @JsonProperty("street")
    private String street;

    @JsonProperty("houseNumber")
    private String houseNumber;

    @JsonProperty("complement")
    private String complement;

    @JsonProperty("neighborhood")
    private String neighborhood;

    @JsonProperty("cep")
    private String cep;

    @JsonCreator
    public Client(
            @JsonProperty("name") String name,
            @JsonProperty("phone") String phone,
            @JsonProperty("cpf") String cpf,
            @JsonProperty("city") String city,
            @JsonProperty("street") String street,
            @JsonProperty("houseNumber") String houseNumber,
            @JsonProperty("complement") String complement,
            @JsonProperty("neighborhood") String neighborhood,
            @JsonProperty("cep") String cep) {
        this.setId(generateUniqueClientId());
        this.name = name.trim().isEmpty() ? "SEM NOME" : name.trim().toUpperCase();
        this.phone = phone.trim().isEmpty() ? "SEM TELEFONE" : phone.trim();
        this.cpf = cpf.trim().isEmpty() ? "SEM CPF" : cpf.trim();
        this.city = city.trim().isEmpty() ? "SEM CIDADE" : city.trim().toUpperCase();
        this.street = street.trim().isEmpty() ? "SEM RUA" : street.trim().toUpperCase();
        this.houseNumber = houseNumber.trim().isEmpty() ? "SEM NUMERO" : houseNumber.trim();
        this.complement = complement.trim().isEmpty() ? "SEM COMPLEMENTO" : complement.trim().toUpperCase();
        this.neighborhood = neighborhood.trim().isEmpty() ? "SEM BAIRRO" : neighborhood.trim().toUpperCase();
        this.cep = cep.trim().isEmpty() ? "SEM CEP" : cep.trim().toUpperCase();
    }

    //
    private int generateUniqueClientId() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            Path fileLocation = Json.getClientsFileLocation();
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

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCep() {
        return cep;
    }

    //
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return String.format("Client{name='%s', phone='%s', cpf='%s', city='%s', street='%s', number='%s', complement='%s', neighborhood='%s', zip='%s'}",
                name, phone, cpf, city, street, houseNumber, complement, neighborhood, cep);
    }
}
