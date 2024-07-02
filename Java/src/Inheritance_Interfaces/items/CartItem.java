package Inheritance_Interfaces.items;

import Inheritance_Interfaces.management.InventoryItem;

public class CartItem extends InventoryItem {

    private static long cartItemCounter = 1;
    private long cartItemId;
    public CartItem(String name, String category, double price, int quantity, long itemId) {
        super(name, category, price, quantity);
        this.setItemId(itemId);
        this.cartItemId = cartItemCounter++;
    }


    public long getCartItemId() {
        return cartItemId;
    }

    @Override
    public String getDetails() {
        return "Cart Item ID " + getCartItemId() + ", Name: " + getName() + ", Category: " + getCategory() + ", Price: " + getPrice() + ", Quantity: " + getQuantity();
    }
}
