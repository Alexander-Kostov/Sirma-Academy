package Inheritance_Interfaces.management;

import Inheritance_Interfaces.items.InventoryItem;
import Inheritance_Interfaces.order.OrderOperations;

import java.io.*;
import java.util.Scanner;

public class MenuOperations {
    private final Scanner scanner = new Scanner(System.in);
    private final InventoryManager inventoryOperations;
    private final OrderOperations orderOperations;


    public MenuOperations(InventoryManager inventoryOperations, OrderOperations orderOperations) {
        this.inventoryOperations = inventoryOperations;
        this.orderOperations = orderOperations;
    }

    public static void displayMenu() {
        System.out.println("You are at the Main Menu! Select one of the following commands:");
        System.out.println();
        System.out.println("1. Add item to inventory");
        System.out.println("2. Remove item from inventory");
        System.out.println("3. Display items");
        System.out.println("4. Categorize items");
        System.out.println("5. Make an order");
        System.out.println("6. Add item to specific order");
        System.out.println("7. Remove item from specific order");
        System.out.println("8. Show all orders");
        System.out.println("9. Save inventory to a file");
        System.out.println("10. Load inventory from a file");
        System.out.println("11. Display menu");
        System.out.println("12. Exit");
        System.out.print("Enter your choice: ");
    }

    public void start() {
        System.out.println("Welcome to Inventory manager!");
        displayMenu();

        while (true) {
            try {
                int command = Integer.parseInt(scanner.nextLine());

                switch (command) {
                    case 1:
                        inventoryOperations.addItemToInventory();
                        break;
                    case 2:
                        inventoryOperations.removeItemFromInventory();
                        break;
                    case 3:
                        inventoryOperations.displayInventoryItems();
                        break;
                    case 4:
                        inventoryOperations.categorizeItems();
                        break;
                    case 5:
                        orderOperations.placeOrder();
                        break;
                    case 6:
                        orderOperations.addItemToExistingOrder();
                        break;
                    case 7:
                        orderOperations.removeItemFromOrder();
                        break;
                    case 8:
                        orderOperations.showOrders();
                        break;
                    case 9:
                        saveToFile("inventory.csv");
                        break;
                    case 10:
                        loadFromFile("inventory.csv");
                        break;
                    case 11:
                        displayMenu();
                        break;
                    case 12:
                        return;
                    default:
                        System.out.println("Invalid command. Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("You entered a string instead of number");
                System.out.println(e.getMessage());
                System.out.println("The app works only with the commands listed: 1-12");
            }
        }
    }

    private void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ItemID,Name,Category,Price,Quantity,Perished,Broken");
            writer.newLine();
            for (InventoryItem item : this.inventoryOperations.getInventoryItems()) {
                writer.write(item.getId() + "," +
                        item.getName() + "," +
                        item.getCategory() + "," +
                        item.getPrice() + "," +
                        item.getQuantity() + "," +
                        item.isPerishable() + "," +
                        item.isBreakable());
                writer.newLine();
            }
            System.out.println("Successfully saved to " + filename);
            System.out.println("You are back to the main menu");
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    private void loadFromFile(String filename) {
        this.inventoryOperations.getInventoryItems().clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // Read the header line
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 9) {
                    long itemId = Long.parseLong(fields[0]);
                    String name = fields[1];
                    String category = fields[2];
                    double price = Double.parseDouble(fields[3]);
                    int quantity = Integer.parseInt(fields[4]);
                    boolean perished = Boolean.parseBoolean(fields[5]);
                    boolean broken = Boolean.parseBoolean(fields[6]);

                    InventoryItem item = new InventoryItem(name, category, price, quantity);
                    item.setItemId(itemId);
                    this.inventoryOperations.getInventoryItems().add(item);
                }
            }
            System.out.println("Successfully loaded from " + filename);
            System.out.println("You are back to the main menu");
        } catch (IOException e) {
            System.err.println("Error loading from file: " + e.getMessage());
            System.out.println("You are back to the main menu");
        }
    }
}
