package Inheritance_Interfaces.items;

import Inheritance_Interfaces.management.InventoryItem;

public class GroceryItem extends InventoryItem {

    private boolean perished;
    public GroceryItem(String name, String category, double price, int quantity) {
        super(name, category, price, quantity);
        this.perished = false;
        setPerishable(true);
    }

    public boolean isPerished() {
        return perished;
    }

    public void setPerished(boolean perished) {
        this.perished = perished;
    }

    @Override
    public boolean isPerishable() {
        return super.isPerishable();
    }

    @Override
    public void setPerishable(boolean perishable) {
        super.setPerishable(perishable);
    }
}
