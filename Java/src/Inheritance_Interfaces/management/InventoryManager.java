package Inheritance_Interfaces.management;

import Inheritance_Interfaces.items.ElectronicsItem;
import Inheritance_Interfaces.items.FragileItem;
import Inheritance_Interfaces.items.GroceryItem;
import Inheritance_Interfaces.order.Order;
import Inheritance_Interfaces.payment.CreditCardPayment;
import Inheritance_Interfaces.payment.PaymentProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InventoryManager {
    private final Scanner scanner = new Scanner(System.in);
    private final List<InventoryItem> inventoryItems = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private PaymentProcessor paymentProcessor = new PaymentProcessor();

    public void start() {
        System.out.println("Welcome to Inventory manager!");
        displayMenu();

        while (true) {
            int command = Integer.parseInt(scanner.nextLine());

            switch (command) {
                case 1:
                    addItemToInventory();
                    break;
                case 2:
                    removeItemFromInventory();
                    break;
                case 3:
                    displayInventoryItems();
                    break;
                case 4:
                    categorizeItems();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 6:
                    addItemToOrder();
                    break;
                case 7:
                    removeItemFromOrder();
                    break;
                case 8:
                    showOrders();
                    break;
                case 9:
                    saveToFile("invetory.csv");
                    break;
                case 10:
                    loadFromFile("invetory.csv");
                    break;
                case 11:
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private void addItemToInventory() {
        String category = pickCategory();

        if (category.equals("exit")) {
            return;
        }

        while (true) {
            if (category.equals("Fragile")) {
                System.out.println("Please enter the following properties: Name, Price, " +
                        "Quantity, Weight");
            } else {
                System.out.println("Please enter the following properties: Name, Price, " +
                        "Quantity");
            }

            String itemData = scanner.nextLine();

            if (itemData.trim().equals("11")) {
                System.out.println("You are back to the main menu");
                break;
            }

            if (validateItem(itemData, category)) {
                String[] itemParts = itemData.split(", ");
                String name = itemParts[0];
                double price = Double.parseDouble(itemParts[1]);
                int quantity = Integer.parseInt(itemParts[2]);

                switch (category) {
                    case "Fragile" -> {
                        double weight = Double.parseDouble(itemParts[3]);
                        FragileItem fragileItem = new FragileItem(name, category, price, quantity, weight);
                        inventoryItems.add(fragileItem);
                        System.out.println(name + " added to the inventory");
                        System.out.println("You are back to the main menu");
                    }
                    case "Electronics" -> {
                        ElectronicsItem electronicsItem = new ElectronicsItem(name, category, price, quantity);
                        inventoryItems.add(electronicsItem);
                        System.out.println(name + " added to the inventory");
                        System.out.println("You are back to the main menu");
                    }
                    case "Grocery" -> {
                        GroceryItem groceryItem = new GroceryItem(name, category, price, quantity);
                        inventoryItems.add(groceryItem);
                        System.out.println(name + " added to the inventory");
                        System.out.println("You are back to the main menu");
                    }
                }
                break; // Break the loop if item is successfully created
            } else {
                System.out.println("Please try again or exit with command 11.");
            }

        }
    }

    private void removeItemFromInventory() {
        if (inventoryItems.isEmpty()) {
            System.out.println("You cannot remove from empty inventory");
            return;
        }

        System.out.println("Please enter the ID of the item you want to remove");
        System.out.println("Here are all the present items in the inventory:");
        displayInventoryItems();
        while (true) {
            try {
                int itemId = Integer.parseInt(scanner.nextLine());
                if (itemId == -1) {
                    System.out.println("You are back to the main menu");
                    return;
                }
                Optional<InventoryItem> itemToBeRemoved =
                        inventoryItems.stream().filter(item -> item.getId() == itemId).findFirst();

                if (itemToBeRemoved.isPresent()) {
                    inventoryItems.remove(itemToBeRemoved.get());
                    System.out.println("Item with ID " + itemId + " removed!");
                    return;
                } else {
                    System.out.println("There is no such item with ID " + itemId);
                    System.out.println("Please enter a valid ID or go back to the main menu with '11'");
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("Please insert a valid ID");
            }

        }
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ItemID,Name,Category,Price,Quantity,Perished,Broken");
            writer.newLine();
            for (InventoryItem item : inventoryItems) {
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

    public void loadFromFile(String filename) {
        inventoryItems.clear();
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
                    inventoryItems.add(item);
                }
            }
            System.out.println("Successfully loaded from " + filename);
            System.out.println("You are back to the main menu");
        } catch (IOException e) {
            System.err.println("Error loading from file: " + e.getMessage());
            System.out.println("You are back to the main menu");
        }
    }

    public void displayInventoryItems() {
        if (inventoryItems.isEmpty()) {
            System.out.println("There are no items to be listed.");
            System.out.println("You are back to the main menu");
            return;
        }
        for (InventoryItem item : inventoryItems) {
            System.out.println(item.getDetails());
        }
    }

    private void removeItemFromOrder() {

    }

    private void addItemToOrder() {

    }

    private void showOrders() {
        for (Order order : this.orders) {
            System.out.println(order);
        }
    }

    private boolean validateItem(String itemData, String category) {
        if (itemData == null || itemData.isEmpty()) {
            System.out.println("Invalid data. Please enter the following data: Name, Category, Price, Quantity");
            displayMenu();
            return false;
        }

        String[] itemParts = itemData.split(", ");

        if (itemParts.length < 3) {
            System.out.println("Invalid number of parameters");
            return false;
        } else {
            try {
                String name = itemParts[0];
                double price = Double.parseDouble(itemParts[1]);
                int quantity = Integer.parseInt(itemParts[2]);

                if (category.equals("Fragile")) {
                    double weight = Double.parseDouble(itemParts[3]);
                }

            } catch (NumberFormatException e) {
                System.out.println("You attempted to enter an invalid number.");
                return false;
            }
        }

        return true;
    }

    private String pickCategory() {
        System.out.println("Please choose from the following categories: ");
        System.out.println("\t Electronics");
        System.out.println("\t Fragile");
        System.out.println("\t Grocery");

        String input = scanner.nextLine();

        while (true) {
            if (!input.trim().equals("Electronics") && !input.trim().equals("Fragile") && !input.trim().equals("Grocery")) {
                System.out.println("Invalid category. Please enter a valid category or exit with command 11");
                input = scanner.nextLine();

                if (input.trim().equals("11")) {
                    return "exit";
                }
            } else {
                System.out.println("Picked " + input);
                return input;
            }
        }
    }

    private void categorizeItems() {
        filterItemsByCategory("Electronics");
        filterItemsByCategory("Fragile");
        filterItemsByCategory("Grocery");
    }

    private void placeOrder() {
        List<InventoryItem> cart = new ArrayList<>();
        while (true) {
            if (inventoryItems.isEmpty()) {
                System.out.println("There are no items in the inventory! Please add items and before placing " +
                        "an order!");
                displayMenu();
                return;
            }

            System.out.println("Enter the item ID to add to the cart or type 'done' to finish:");
            System.out.println("Items: ");
            displayInventoryItems();
            String input = scanner.nextLine();

            if (input.equals("done")) {
                break;
            }

            try {
                long itemId = Long.parseLong(input);
                InventoryItem item = inventoryItems.stream()
                        .filter(i -> i.getId() == itemId)
                        .findFirst()
                        .orElse(null);

                if (item == null) {
                    System.out.println("Item not found!");
                    continue;
                }

                System.out.println("Enter the quantity:");
                int quantity = Integer.parseInt(scanner.nextLine());

                if (quantity > item.getQuantity()) {
                    System.out.println("Not enough stock!");
                } else {
                    item.setQuantity(item.getQuantity() - quantity);
                    cart.add(new InventoryItem(item.getName(), item.getCategory(), item.getPrice(), quantity));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }

        if (cart.isEmpty()) {
            System.out.println("Cart is empty!");
            displayMenu();
            return;
        }

        double total = cart.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
        System.out.println("Order total: $" + total);

        while (true) {
            System.out.println("Choose payment method: ");
            System.out.println("\t1. Credit Card");
            System.out.println("\t2. PayPal");

            try {
                int paymentMethod = Integer.parseInt(scanner.nextLine());

                if (paymentMethod == 1) {
                    System.out.println("Please enter the following data: Card number, Card holder, Expiry date, CSV " +
                            "Number");
                    System.out.println("Example Data: 4539 1488 0343 6467, John Doe, 12/25, 123");
                    String input = scanner.nextLine();
                    if (!validateCreditCardInputParameters(input)) {
                        continue;
                    }
                    String[] inputData = input.split(", ");
                    CreditCardPayment creditCardPayment = new CreditCardPayment(inputData[0], inputData[1],
                            inputData[2], inputData[3]);
                    if (!paymentProcessor.processPayment(creditCardPayment)) {
                        continue;
                    }
                    Order order = new Order(cart, total);
                    this.orders.add(order);
                    displayMenu();
                    return;
                } else if (paymentMethod == 2) {

                } else {
                    throw new NumberFormatException("Invalid payment method!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid method! ");
                System.out.println(e.getMessage());
            }

        }

    }

    private boolean validateCreditCardInputParameters(String input) {
        String[] data = input.split(", ");
        if (data.length != 4) {
            System.out.println("Invalid number of parameters. Please try again");
            return false;
        }
        return true;
    }

    private void filterItemsByCategory(String category) {
        List<InventoryItem> filtered =
                inventoryItems.stream().filter(i -> i.getCategory().equals(category)).collect(Collectors.toList());

        if (!filtered.isEmpty()) {
            System.out.println(category);
            filtered.forEach(item -> {
                System.out.printf("\t%s\n", item.getDetails());
            });
        }
    }

    private void displayMenu() {
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
        System.out.println("11. Exit");
        System.out.print("Enter your choice: ");
    }

    private void removeItemById(long id) {
        inventoryItems.removeIf(item -> item.getId() == id);
    }

}
