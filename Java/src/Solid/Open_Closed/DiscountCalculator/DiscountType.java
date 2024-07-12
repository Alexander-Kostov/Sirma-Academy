package Solid.Open_Closed.DiscountCalculator;

public enum DiscountType {
    STUDENT(0.1),
    SENIOR(0.2),
    ELDER(0.3);

    private double discount;
    DiscountType(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
