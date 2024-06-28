package Inheritance_Interfaces.items;

import Inheritance_Interfaces.management.InventoryItem;

public class GroceryItem extends InventoryItem {
    public GroceryItem(String name, String category, boolean breakable, boolean perishable, double price,
                       int quantity) {
        super(name, category, breakable, perishable, price, quantity);
    }
}
