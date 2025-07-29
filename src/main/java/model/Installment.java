package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import enums.InstallmentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Installment {

    @JsonProperty("installmentNumber")
    private final int installmentNumber;

    @JsonProperty("originalValue")
    private final BigDecimal originalValue;

    @JsonProperty("amountPaid")
    private BigDecimal amountPaid;

    @JsonProperty("dueDate")
    private final LocalDate dueDate;

    @JsonProperty("status")
    private InstallmentStatus status;

    @JsonCreator
    public Installment(
            @JsonProperty("installmentNumber") int installmentNumber,
            @JsonProperty("originalValue") BigDecimal originalValue,
            @JsonProperty("amountPaid") BigDecimal amountPaid,
            @JsonProperty("dueDate") LocalDate dueDate,
            @JsonProperty("status") InstallmentStatus status) {

        this.installmentNumber = installmentNumber;
        this.originalValue = originalValue;
        this.amountPaid = amountPaid != null ? amountPaid : BigDecimal.ZERO;
        this.dueDate = dueDate;
        this.status = status != null ? status : InstallmentStatus.PENDING;
    }

    public Installment(int installmentNumber, BigDecimal originalValue, LocalDate dueDate) {
        this(installmentNumber, originalValue, BigDecimal.ZERO, dueDate, InstallmentStatus.PENDING);
    }

    public void addPayment(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }

        this.amountPaid = this.amountPaid.add(value);

        int comparison = this.amountPaid.compareTo(this.originalValue);

        if (comparison >= 0) {
            this.status = InstallmentStatus.PAID;
            this.amountPaid = this.originalValue;
        } else {
            this.status = InstallmentStatus.PARTIALLY_PAID;
        }
    }

    // Getters
    public int getInstallmentNumber() {
        return installmentNumber;
    }

    public BigDecimal getOriginalValue() {
        return originalValue;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public InstallmentStatus getStatus() {
        return status;
    }

    @JsonIgnore
    public BigDecimal getOutstandingBalance() {
        return originalValue.subtract(amountPaid);
    }

    // Setters
    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid != null ? amountPaid : BigDecimal.ZERO;
    }

    public void setStatus(InstallmentStatus status) {
        this.status = status;
    }
}
