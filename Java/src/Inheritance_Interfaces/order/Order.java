package Inheritance_Interfaces.order;

import Inheritance_Interfaces.management.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<InventoryItem> items;
    private boolean isCompleted;

    public Order() {
        this.items = new ArrayList<>();
        this.isCompleted = false;
    }

    public void addItem(InventoryItem item) {
        items.add(item);
    }

    public void removeItem(InventoryItem item) {
        items.remove(item);
    }

    public double calculateTotal() {
        double total = 0.0;

        for (InventoryItem item : items) {
            total += item.calculatingValue();
        }

        return total;
    }
}
