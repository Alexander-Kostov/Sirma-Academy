package Inheritance_Interfaces;

import Inheritance_Interfaces.items.ElectronicsItem;
import Inheritance_Interfaces.items.FragileItem;
import Inheritance_Interfaces.items.GroceryItem;
import Inheritance_Interfaces.management.InventoryManager;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

//        InventoryItem item1 = new InventoryItem("Laptop Acer Nitro", "Electronics", true, false, 299.99, 5);
//        InventoryItem item2 = new InventoryItem("Banana", "Groceries", false, true, 19.99, 20);
//        InventoryItem item3 = new InventoryItem("Cup", ""true, false, 49.99, 10);

        ElectronicsItem laptop = new ElectronicsItem("Laptop Acer Nitro", "Electronics", true, false, 299.99, 5);
        GroceryItem banana = new GroceryItem("Banana", "Groceries", false, true, 19.99, 20);
        FragileItem cup = new FragileItem("Cup", "Fragile", true, false, 49.99, 10);

        manager.addItem(laptop);
        manager.addItem(banana);
        manager.addItem(cup);

        manager.saveToFile("inventory.csv");

        manager.loadFromFile("inventory.csv");
        manager.displayItems();
    }
}
