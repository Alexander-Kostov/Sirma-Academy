package Inheritance_Interfaces.management;

import Inheritance_Interfaces.items.AbstractItem;

public class InventoryItem extends AbstractItem {
    private static long idCounter = 0;
    private long itemId;
    private int quantity;

    public InventoryItem(String name, String category, boolean breakable, boolean perishable, double price,
                         int quantity) {
        super(name, category, breakable, perishable, price);
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
        return "ID " + itemId + ", Category: " + getCategory() + ", Price: " + getPrice() + ", Quantity: " + quantity;
    }

    @Override
    public double calculatingValue() {
        return getPrice() * quantity;
    }

    @Override
    public String getDescription() {
        String description = "";

        if (isPerishable()) {
            description += "The item is perishable!\n";

            if (isPerished()) {
                description += "The item has perished!\n";
            } else {
                description += "The item has not perished!\n";
            }
        } else {
            description += "The item is not perishable\n";
        }

        if (isBreakable()) {
            description += "The item is breakable!\n";

            if (isBroken()) {
                description += "The item is broken!\n";
            } else {
                description += "The item is not broken!\n";
            }
        } else {
            description += "The item is not breakable!\n";
        }

        return description;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
