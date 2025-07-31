package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class ProductSold {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("total")
    private BigDecimal total;

    @JsonCreator
    public ProductSold(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("quantity") int quantity, @JsonProperty("total") BigDecimal total) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.total = total;
    }

    //getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    //
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
