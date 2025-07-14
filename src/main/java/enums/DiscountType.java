package enums;

public enum DiscountType {
    NONE("Não"),
    PERCENTAGE("Percentual"),
    ABSOLUT("Absoluto");

    private final String discountTypeDescription;

    private DiscountType(String discountTypeDescription) {
        this.discountTypeDescription = discountTypeDescription;
    }

    @Override
    public String toString() {
        return discountTypeDescription;
    }
}
