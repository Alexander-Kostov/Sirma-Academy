package Inheritance_Interfaces.items;

import Inheritance_Interfaces.management.InventoryItem;

public class FragileItem extends InventoryItem {

    private double weight;
    public FragileItem(String name, String category, boolean breakable, boolean perishable, double price,
                       int quantity) {
        super(name, category, breakable, perishable, price, quantity);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
