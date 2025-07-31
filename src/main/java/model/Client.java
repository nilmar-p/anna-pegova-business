package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import utils.Json;

public class Client {

    @JsonProperty("id")
    private String id;

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

    // desserialization
    @JsonCreator
    public Client(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("phone") String phone,
            @JsonProperty("cpf") String cpf,
            @JsonProperty("city") String city,
            @JsonProperty("street") String street,
            @JsonProperty("houseNumber") String houseNumber,
            @JsonProperty("complement") String complement,
            @JsonProperty("neighborhood") String neighborhood,
            @JsonProperty("cep") String cep) {

        this.id = id;
        this.name = (name == null || name.trim().isEmpty()) ? "SEM NOME" : name.trim().toUpperCase();
        this.phone = (phone == null || phone.trim().isEmpty()) ? "SEM TELEFONE" : phone.trim();
        this.cpf = (cpf == null || cpf.trim().isEmpty()) ? "SEM CPF" : cpf.trim();
        this.city = (city == null || city.trim().isEmpty()) ? "SEM CIDADE" : city.trim().toUpperCase();
        this.street = (street == null || street.trim().isEmpty()) ? "SEM RUA" : street.trim().toUpperCase();
        this.houseNumber = (houseNumber == null || houseNumber.trim().isEmpty()) ? "SEM NUMERO" : houseNumber.trim();
        this.complement = (complement == null || complement.trim().isEmpty()) ? "SEM COMPLEMENTO" : complement.trim().toUpperCase();
        this.neighborhood = (neighborhood == null || neighborhood.trim().isEmpty()) ? "SEM BAIRRO" : neighborhood.trim().toUpperCase();
        this.cep = (cep == null || cep.trim().isEmpty()) ? "SEM CEP" : cep.trim().toUpperCase();
    }

    // new client
    public Client(String name, String phone, String cpf, String city, String street,
            String houseNumber, String complement, String neighborhood, String cep) {

        this.id = generateUniqueClientId();
        this.name = (name == null || name.trim().isEmpty()) ? "SEM NOME" : name.trim().toUpperCase();
        this.phone = (phone == null || phone.trim().isEmpty()) ? "SEM TELEFONE" : phone.trim();
        this.cpf = (cpf == null || cpf.trim().isEmpty()) ? "SEM CPF" : cpf.trim();
        this.city = (city == null || city.trim().isEmpty()) ? "SEM CIDADE" : city.trim().toUpperCase();
        this.street = (street == null || street.trim().isEmpty()) ? "SEM RUA" : street.trim().toUpperCase();
        this.houseNumber = (houseNumber == null || houseNumber.trim().isEmpty()) ? "SEM NUMERO" : houseNumber.trim();
        this.complement = (complement == null || complement.trim().isEmpty()) ? "SEM COMPLEMENTO" : complement.trim().toUpperCase();
        this.neighborhood = (neighborhood == null || neighborhood.trim().isEmpty()) ? "SEM BAIRRO" : neighborhood.trim().toUpperCase();
        this.cep = (cep == null || cep.trim().isEmpty()) ? "SEM CEP" : cep.trim().toUpperCase();
    }

    //
    private String generateUniqueClientId() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Path fileLocation = Json.getProductsFileLocation();
            Files.createDirectories(fileLocation.getParent());

            List<Product> products = Files.exists(fileLocation)
                    ? mapper.readValue(fileLocation.toFile(), new TypeReference<List<Product>>() {
                    })
                    : new ArrayList<>();

            Set<String> existingIds = products.stream()
                    .map(Product::getId)
                    .collect(Collectors.toSet());

            String newId;
            do {
                newId = UUID.randomUUID().toString().substring(0, 8);
            } while (existingIds.contains(newId));

            return newId;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar ID do produto!", "ERRO", JOptionPane.ERROR_MESSAGE);
            return UUID.randomUUID().toString().substring(0, 8);
        }
    }

    // Getters e Setters
    public String getId() {
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
    public void setId(String id) {
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
