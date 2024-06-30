package Inheritance_Interfaces.order;

import Inheritance_Interfaces.management.InventoryItem;
import Inheritance_Interfaces.payment.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static long nextOrderId = 1;

    private List<InventoryItem> items;
    private double totalCost;
    private long orderId;

    public Order(List<InventoryItem> items, double totalCost) {
        this.items = items;
        this.totalCost = totalCost;
        this.orderId = nextOrderId++;
    }

    public void addItem(InventoryItem item) {
        items.add(item);
    }

    public void removeItem(InventoryItem item) {
        items.remove(item);
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void updateInventoryQuantities(List<InventoryItem> inventory) {
        for (InventoryItem orderItem : items) {
            for (InventoryItem inventoryItem : inventory) {
                if (orderItem.getId() == inventoryItem.getId()) {
                    inventoryItem.setQuantity(inventoryItem.getQuantity() - orderItem.getQuantity());
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Order ID: ").append(orderId).append(System.lineSeparator());
        sb.append("Total cost: ").append(String.format("%.2f", totalCost)).append(System.lineSeparator());

        for (InventoryItem item : items) {
            sb.append("\t").append(item.getDetails()).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
