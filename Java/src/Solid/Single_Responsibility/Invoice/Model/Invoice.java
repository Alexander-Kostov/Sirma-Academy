package Solid.Single_Responsibility.Invoice.Model;

public class Invoice {
    private double amount;
    private String customerName;

    public Invoice(double amount, String customerName) {
        this.amount = amount;
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Customer name: " + customerName + "\n" + "with amount - " + amount + "\n";
    }
}
