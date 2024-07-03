package Inheritance_Interfaces;

import Inheritance_Interfaces.management.InventoryManager;
import Inheritance_Interfaces.management.MenuOperations;
import Inheritance_Interfaces.order.OrderOperations;
import Inheritance_Interfaces.utils.InputValidation;

public class Main {
    public static void main(String[] args) {
        InventoryManager inventoryOperations = new InventoryManager();
        InputValidation inputValidation = new InputValidation();
        OrderOperations orderOperations = new OrderOperations(inventoryOperations, inputValidation);
        MenuOperations menuOperations = new MenuOperations(inventoryOperations, orderOperations);

        menuOperations.start();
    }
}
