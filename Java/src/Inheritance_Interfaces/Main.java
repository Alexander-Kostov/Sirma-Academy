package Inheritance_Interfaces;

import Inheritance_Interfaces.items.ElectronicsItem;
import Inheritance_Interfaces.items.FragileItem;
import Inheritance_Interfaces.items.GroceryItem;
import Inheritance_Interfaces.management.InventoryManager;
import Inheritance_Interfaces.management.InventoryOperations;
import Inheritance_Interfaces.management.MenuOperations;
import Inheritance_Interfaces.order.OrderOperations;
import Inheritance_Interfaces.utils.InputValidation;

public class Main {
    public static void main(String[] args) {
        InventoryOperations inventoryOperations = new InventoryOperations();
        InputValidation inputValidation = new InputValidation();
        OrderOperations orderOperations = new OrderOperations(inventoryOperations, inputValidation);
        MenuOperations menuOperations = new MenuOperations(inventoryOperations, orderOperations);

        menuOperations.start();
    }
}
