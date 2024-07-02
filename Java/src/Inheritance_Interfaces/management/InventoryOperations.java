package Inheritance_Interfaces.management;

import Inheritance_Interfaces.items.*;
import Inheritance_Interfaces.utils.InputValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InventoryOperations {
    private final List<InventoryItem> inventoryItems = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    private final InputValidation inputValidation = new InputValidation();

    public void addItemToInventory() {
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

            if (itemData.trim().equals("exit")) {
                System.out.println("You are back to the main menu");
                break;
            }

            if (inputValidation.validateItem(itemData, category)) {
                String[] itemParts = itemData.split(", ");
                String name = itemParts[0];
                double price = Double.parseDouble(itemParts[1]);
                int quantity = Integer.parseInt(itemParts[2]);

                switch (category) {
                    case "Fragile" -> {
                        double weight = Double.parseDouble(itemParts[3]);
                        FragileItem fragileItem = new FragileItem(name, category, price, quantity, weight);
                        inventoryItems.add(fragileItem);
                    }
                    case "Electronics" -> {
                        ElectronicsItem electronicsItem = new ElectronicsItem(name, category, price, quantity);
                        inventoryItems.add(electronicsItem);
                    }
                    case "Grocery" -> {
                        GroceryItem groceryItem = new GroceryItem(name, category, price, quantity);
                        inventoryItems.add(groceryItem);
                    }
                }
                System.out.println(name + " added to the inventory");
                System.out.println("You are back to the main menu");
                break; // Break the loop if item is successfully created
            } else {
                System.out.println("Please try again or go back to main menu with command 'exit'.");
            }

        }
    }

    public void removeItemFromInventory() {
        if (checkIfInventoryIsEmpty("You cannot remove from empty inventory")) {
            return;
        }

        System.out.println("Please enter the ID of the item you want to remove");
        System.out.println("Here are all the present items in the inventory:");
        displayInventoryItems();

        InventoryItem item = findItemById();
        if (item != null) {
            inventoryItems.remove(item);
            System.out.println("Item successfully removed from inventory!");
            System.out.println("You are back at the main menu.");
        }
    }

    public void displayInventoryItems() {
        if (checkIfInventoryIsEmpty("There are no present items in the inventory")) {
            System.out.println("You are back to the main menu");
            return;
        }
        for (InventoryItem item : inventoryItems) {
            System.out.println(item.getDetails());
        }
    }

    public void categorizeItems() {
        filterItemsByCategory("Electronics");
        filterItemsByCategory("Fragile");
        filterItemsByCategory("Grocery");
    }

    public InventoryItem findItemById() {
        String itemIdInput = scanner.nextLine();
        while (true) {
            try {
                if (itemIdInput.equals("exit")) {
                    System.out.println("You are back at the main menu.");
                    return null;
                }
                int itemId = Integer.parseInt(itemIdInput);
                Optional<InventoryItem> optionalInventoryItem = inventoryItems.stream().filter(i -> i.getId() == itemId).findFirst();

                if (optionalInventoryItem.isPresent()) {
                    InventoryItem inventoryItem = optionalInventoryItem.get();
                    return inventoryItem;
                } else {
                    System.out.println("There is no such item with ID " + itemId);
                    System.out.println("Please try again with an existing ID or go back to main menu with 'exit'.");
                    itemIdInput = scanner.nextLine();
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid ID or go back to main menu with 'exit'.");
                itemIdInput = scanner.nextLine();
            }
        }
    }
    public boolean checkIfInventoryIsEmpty(String message) {
        if (inventoryItems.isEmpty()) {
            System.out.println(message);
            System.out.println("You can add an item by selecting option '1'.");
            return true;
        }
        return false;
    }

    private String pickCategory() {
        System.out.println("Please choose from the following categories: ");
        System.out.println("\t Electronics");
        System.out.println("\t Fragile");
        System.out.println("\t Grocery");

        String input = scanner.nextLine();

        while (true) {
            if (!input.trim().equals("Electronics") && !input.trim().equals("Fragile") && !input.trim().equals("Grocery")) {
                System.out.println("Invalid category. Please enter a valid category or go back to main menu with " +
                        "command 'exit'");
                input = scanner.nextLine();

                if (input.trim().equals("exit")) {
                    System.out.println("You are back at the main menu");
                    return "exit";
                }
            } else {
                System.out.println("Picked " + input);
                return input;
            }
        }
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

    public void selectQuantity(InventoryItem item, List<CartItem> cart) {
        System.out.println("Enter the quantity:");
        int quantity = Integer.parseInt(scanner.nextLine());

        if (quantity > item.getQuantity()) {
            System.out.println("Not enough stock!");
        } else {
            item.setQuantity(item.getQuantity() - quantity);
            CartItem cartItem = new CartItem(item.getName(), item.getCategory(), item.getPrice(), quantity, item.getId());
            cart.add(cartItem);
        }
    }

    public List<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }
}
