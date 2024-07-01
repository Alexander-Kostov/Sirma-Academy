package Inheritance_Interfaces.management;

import Inheritance_Interfaces.items.AbstractItem;

public class InventoryItem extends AbstractItem {
    private static long idCounter = 0;
    private long itemId;
    private int quantity;

    public InventoryItem(String name, String category, double price,
                         int quantity) {
        super(name, category, price);
        this.itemId = ++idCounter;
        this.quantity = quantity;
    }

    public long getId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getDetails() {
        return "ID " + getId() + ", Name: " + getName() + ", Category: " + getCategory() + ", Price: " + getPrice() + ", Quantity: " + quantity;
    }

    @Override
    public double calculatingValue() {
        return getPrice() * quantity;
    }


    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
