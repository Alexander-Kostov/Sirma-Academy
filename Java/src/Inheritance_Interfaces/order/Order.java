package Inheritance_Interfaces.order;

import Inheritance_Interfaces.items.CartItem;
import Inheritance_Interfaces.management.InventoryItem;
import Inheritance_Interfaces.payment.PaymentMethod;
import Inheritance_Interfaces.payment.PaymentProcessor;

import java.util.List;

public class Order {
    private static long nextOrderId = 1;
    private List<CartItem> items;
    private double totalCost;
    private long orderId;

    private PaymentMethod paymentMethod;

    public Order(List<CartItem> items, double totalCost, PaymentMethod paymentMethod) {
        this.items = items;
        this.totalCost = totalCost;
        this.orderId = nextOrderId++;
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public List<CartItem> getItems() {
        return this.items;
    }

    public long getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Order ID: ").append(orderId).append(System.lineSeparator());
        sb.append("Total cost: ").append(String.format("%.2f", totalCost)).append(System.lineSeparator());
        sb.append("Payment method: ").append(paymentMethod.getMethodName()).append(System.lineSeparator());
        sb.append("Cart items:").append(System.lineSeparator());

        for (InventoryItem item : items) {
            sb.append("\t").append(item.getDetails()).append(System.lineSeparator());
        }

        return sb.toString();
    }

    public CartItem findCartItem(long id) {
        return this.items.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }


}
