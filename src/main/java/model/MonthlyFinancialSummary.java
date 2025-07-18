package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonthlyFinancialSummary {

    @JsonProperty("month")
    String month;
    @JsonProperty("year")
    String year;
    @JsonProperty("totalProductsSold")
    int totalProductsSold;
    @JsonProperty("totalSales")
    double totalSales;
    @JsonProperty("totalProfit")
    double totalProfit;

}
