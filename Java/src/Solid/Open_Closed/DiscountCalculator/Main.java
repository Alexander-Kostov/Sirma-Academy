package Solid.Open_Closed.DiscountCalculator;

public class Main {
    public static void main(String[] args) {
        DiscountType discountType = DiscountType.STUDENT;
        System.out.println(calculate(discountType, 100));
    }

    public static double calculate(DiscountType type, double price) {
        return price - type.getDiscount() * price;
    }

}
