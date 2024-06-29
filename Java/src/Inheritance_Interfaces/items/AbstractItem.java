package Inheritance_Interfaces.items;

import Inheritance_Interfaces.items.interfaces.*;

public abstract class AbstractItem implements Item, Categorizable, Breakable, Perishable, Sellable {
    private String name;
    private String category;
    private boolean perishable;
    private boolean breakable;
    private double price;

    public AbstractItem(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.perishable = false;
        this.breakable = false;
    }

    @Override
    public void handleBreakage() {
//        if (broken) {
//            System.out.println("Oops.. You have just dropped your item.");
//        } else {
//            System.out.println("Your item is as good as new.");
//        }
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean isBreakable() {
        return this.breakable;
    }

    @Override
    public boolean isPerishable() {
        return this.perishable;
    }

    @Override
    public void handleExpiration() {
//        if(perishable) {
//            System.out.println("Item has expired");
//        } else {
//            System.out.println("Item has not expired");
//        }
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
