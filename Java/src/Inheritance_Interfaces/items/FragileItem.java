package Inheritance_Interfaces.items;

public class FragileItem extends InventoryItem {

    private double weight;
    private boolean broken;
    public FragileItem(String name, String category, double price, int quantity, double weight) {
        super(name, category, price, quantity);
        this.weight = weight;
        this.broken = false;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }
}
