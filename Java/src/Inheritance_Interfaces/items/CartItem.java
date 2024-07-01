package Inheritance_Interfaces.items;

import Inheritance_Interfaces.management.InventoryItem;

public class CartItem extends InventoryItem {
    public CartItem(String name, String category, double price, int quantity, long itemId) {
        super(name, category, price, quantity);
        setItemId(itemId);
    }
}
