package Inheritance_Interfaces.management;

import Inheritance_Interfaces.items.ElectronicsItem;
import Inheritance_Interfaces.items.FragileItem;
import Inheritance_Interfaces.items.GroceryItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InventoryManager {
    private Scanner scanner = new Scanner(System.in);
    private List<InventoryItem> items = new ArrayList<>();

    public void addItem(InventoryItem item) {
        items.add(item);
    }

    public void removeItemById(long id) {
        items.removeIf(item -> item.getId() == id);
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ItemID,Name,Category,Price,Quantity,Perished,Broken");
            writer.newLine();
            for (InventoryItem item : items) {
                writer.write(item.getId() + "," +
                        item.getName() + "," +
                        item.getCategory() + "," +
                        item.getPrice() + "," +
                        item.getQuantity() + "," +
                        item.isPerishable() + "," +
                        item.isBreakable());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        items.clear();
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
                    items.add(item);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading from file: " + e.getMessage());
        }
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("There are no items to be listed.");
        }
        for (InventoryItem item : items) {
            System.out.println(item.getDetails());
        }
    }

    public void start() {
        displayMenu();

        while (true) {
            int command = Integer.parseInt(scanner.nextLine());

            switch (command) {
                case 1:
                    addItem();
                    break;
                case 2:
                    displayItems();
                    break;
                case 3:
                    categorizeItems();
                    break;
//                case 4:
//                    placeOrder();
//                    break;
                case 5:
                    saveToFile("invetory.csv");
                    break;
                case 6:
                    loadFromFile("invetory.csv");
                    break;
                case 7:
                    return; // Exit
                default:
                    System.out.println("Invalid command. Please try again.");

            }
        }
    }

    private void addItem() {
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

            if (itemData.trim().equals("10")) {
                System.out.println("Returning to main commands menu.");
                displayMenu();
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
                        items.add(fragileItem);
                        System.out.println(name + " added to the inventory");
                    }
                    case "Electronics" -> {
                        ElectronicsItem electronicsItem = new ElectronicsItem(name, category, price, quantity);
                        items.add(electronicsItem);
                        System.out.println(name + " added to the inventory");
                    }
                    case "Grocery" -> {
                        GroceryItem groceryItem = new GroceryItem(name, category, price, quantity);
                        items.add(groceryItem);
                        System.out.println(name + " added to the inventory");
                    }
                }
                break; // Break the loop if item is successfully created
            } else {
                System.out.println("Please try again or exit with command 10.");
            }

        }
    }

    private boolean validateItem(String itemData, String category) {
        if (itemData == null || itemData.isEmpty()) {
            System.out.println("Invalid data. Please enter the following data: Name, Category, Price, Quantity");
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
                    double weight = Double.parseDouble(itemParts[4]);
                }

            } catch (NumberFormatException e) {
                System.out.println("You attempted to enter an invalid number. Please try again");
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
                System.out.println("Invalid category. Please enter a valid category or exit with command 10");
                input = scanner.nextLine();

                if (input.trim().equals("10")) {
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

    private void filterItemsByCategory(String category) {
        List<InventoryItem> filtered =
                items.stream().filter(i -> i.getCategory().equals(category)).collect(Collectors.toList());

        if (!filtered.isEmpty()) {
            System.out.println(category);
            filtered.forEach(item -> {
                System.out.printf("\t%s\n", item.getDetails());
            });
        }
    }

    private boolean validateInput(String input) {
        if (input == null || input.isBlank()) {
            return false;
        }

        String[] inputParts = input.split(", ");

        if (inputParts.length != 6) {
            System.out.println("Invalid number of parameters");
            return false;
        }

        try {
            String name = inputParts[0];
            if (name == null || name.isEmpty()) {
                System.out.println("Invalid name.");
                return false;
            }
            String category = inputParts[1];
            if (!category.equals("Electronics") && !category.equals("Fragile") && !category.equals("Grocery")) {
                System.out.println("Invalid category. The valid categories are: " + "Electronics, Fragile, Grocery " + "Please try again");
            }
            if (!checksForValidBoolean(inputParts[2])) {
                System.out.println("Invalid boolean value. Valid values are true/false. Please try again");
                return false;
            }
            if (!checksForValidBoolean(inputParts[3])) {
                System.out.println("Please select one of the following categories: 1 - Electronics");
                return false;
            }

            double price = Double.parseDouble(inputParts[4]);
            int quantity = Integer.parseInt(inputParts[5]);

        } catch (NumberFormatException e) {
            System.out.print("Invalid number. ");
            return false;
        }

        return true;
    }

    private static boolean checksForValidBoolean(String value) {
        if (value.equals("true")) {
            return true;
        } else if (value.equals("false")) {
            return true;
        } else {
            return false;
        }
    }

    private void displayMenu() {
        System.out.println("Welcome to Inventory manager! Select one of the following commands");
        System.out.println("1. Add Item");
        System.out.println("2. List Items");
        System.out.println("3. Categorize Items");
        System.out.println("4. Place Order");
        System.out.println("5. Save Inventory");
        System.out.println("6. Load Inventory");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

}
