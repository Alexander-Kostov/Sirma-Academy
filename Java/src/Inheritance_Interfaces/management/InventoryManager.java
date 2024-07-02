package Inheritance_Interfaces.management;

import Inheritance_Interfaces.items.*;
import Inheritance_Interfaces.order.Order;
import Inheritance_Interfaces.payment.CreditCardPayment;
import Inheritance_Interfaces.payment.PayPalPayment;
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
            try {
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
                    addItemToExistingOrder();
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

            if (itemData.trim().equals("exit")) {
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

    private void removeItemFromInventory() {
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

    private void categorizeItems() {
        filterItemsByCategory("Electronics");
        filterItemsByCategory("Fragile");
        filterItemsByCategory("Grocery");
    }

    private void placeOrder() {
        if (checkIfInventoryIsEmpty("You cannot make an order with an empty inventory")) {
            System.out.println("You are back to the main menu");
            return;
        }

        List<CartItem> cart = new ArrayList<>();

        while (true) {

            System.out.println("Enter the item ID to add to the cart or type 'exit' to finish:");
            System.out.println("All Items present: ");
            displayInventoryItems();

            InventoryItem item = findItemById();

            if (item == null) {
                break;
            } else {
                selectQuantity(item, cart);
            }
        }

        if (cart.isEmpty()) {
            System.out.println("Cart is empty!");
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
                    Order order = new Order(cart, total, creditCardPayment);
                    this.orders.add(order);
                    displayMenu();
                    return;
                } else if (paymentMethod == 2) {
                    System.out.println("Please enter the following data: Email, Password");
                    System.out.println("The email should consist of '@' and a domain part");
                    System.out.println("The password should be at least 6 symbols long and it should contain at least" +
                            " one digit and one capital letter");
                    System.out.println("For example: User@gmail.com, User123");
                    String input = scanner.nextLine();
                    String[] inputData = input.split(", ");
                    PayPalPayment payPalPayment = new PayPalPayment(inputData[0], inputData[1]);
                    if (!paymentProcessor.processPayment(payPalPayment)) {
                        continue;
                    }
                    Order order = new Order(cart, total, payPalPayment);
                    this.orders.add(order);
                    displayMenu();
                    return;
                } else {
                    throw new NumberFormatException("Invalid payment method!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid method! ");
                System.out.println(e.getMessage());
            }

        }

    }

    private void selectQuantity(InventoryItem item, List<CartItem> cart) {
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

    private void addItemToExistingOrder() {
        if (checkIfOrdersAreEmpty() || checkIfInventoryIsEmpty("---------")) return;

        Order order = findOrderById();

        if (order == null) {
            return;
        }

        System.out.println("You selected the order with ID " + order.getOrderId());
        System.out.println(order);

            while (true) {
                System.out.println("Enter the item ID to add to the cart or type 'exit' to finish:");
                displayInventoryItems();
                InventoryItem item = findItemById();
                if (item != null) {
                    try {
                        System.out.println("Select quantity: ");
                        String input = scanner.nextLine();
                        if (input.equals("exit")) {
                            System.out.println("You are back to the main menu.");
                            return;
                        }

                        int quantity = Integer.parseInt(input);
                        CartItem cartItem = order.findCartItem(item.getId());
                        if (quantity > item.getQuantity()) {
                            System.out.println("Not enough stock!");
                        } else {
                            if (cartItem != null) {
                                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                                item.setQuantity(item.getQuantity() - quantity);
                            } else {
                                CartItem cartItem1 = new CartItem(item.getName(), item.getCategory(), item.getPrice(),
                                        quantity, item.getId());
                                order.getItems().add(cartItem1);
                                item.setQuantity(item.getQuantity() - quantity);
                                System.out.println("Item with ID " + cartItem1.getCartItemId() + " And with quantity " + cartItem1.getQuantity() +
                                        " Added to the cart");
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number. Please try again with a valid number or go to main menu with " +
                                "'exit'.");
                    }
                } else {
                    return;
                }
            }
    }

    private void removeItemFromOrder() {
        if (checkIfOrdersAreEmpty() || checkIfInventoryIsEmpty("---------")) return;

        Order order = findOrderById();

        if (order == null) {
            return;
        }

        System.out.println("You selected the order with ID " + order.getOrderId());
        System.out.println(order);

        while (true) {
            System.out.println("Enter the item ID to remove from the cart or type 'exit' to finish:");
            displayInventoryItems();
            InventoryItem item = findItemById();
            if (item != null) {
                try {
                    System.out.println("Select quantity to remove: ");
                    String input = scanner.nextLine();
                    if (input.equals("exit")) {
                        System.out.println("You are back to the main menu.");
                        return;
                    }

                    int quantity = Integer.parseInt(input);
                    CartItem cartItem = order.findCartItem(item.getId());
                    if (cartItem == null) {
                        System.out.println("This item is not in the cart!");
                    } else {
                        if (quantity >= cartItem.getQuantity()) {
                            order.getItems().remove(cartItem);
                            item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                            System.out.println("Item with ID " + cartItem.getId() + " has been removed from the cart.");
                        } else {
                            cartItem.setQuantity(cartItem.getQuantity() - quantity);
                            item.setQuantity(item.getQuantity() + quantity);
                            System.out.println("Reduced quantity of item with ID " + cartItem.getId() + " in the cart.");
                        }

                        if (order.getItems().isEmpty()) {
                            orders.remove(order);
                            System.out.println("The cart is now empty. The order has been removed.");
                            return;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please try again with a valid number or go to main menu with 'exit'.");
                }
            } else {
                return;
            }
        }
    }

    private Order findOrderById() {
        System.out.println("Please enter the ID of the specific order:");
        showOrders();
        while (true) {
            String orderInput = scanner.nextLine();
            try {
                int orderId = Integer.parseInt(orderInput);
                if (orderInput.trim().equals("exit")) {
                    return null;
                }
                Optional<Order> optionalOrder = orders.stream().filter(o -> o.getOrderId() == orderId).findFirst();
                if (optionalOrder.isPresent()) {
                    return optionalOrder.get();
                } else {
                    System.out.println("There is no such order with ID " + orderId);
                    System.out.println("Please enter an existing ID or go back to the main menu with 'exit'");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again with valid number or go back to the main menu " +
                        "with 'exit'");
            }
        }
    }

    private InventoryItem findItemById() {
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

    private void showOrders() {
        if (this.orders.isEmpty()) {
            System.out.println("There are no present orders");
            return;
        }
        System.out.println("All present orders:");
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
                if (name.isBlank()) {
                    System.out.println("Name cannot be empty!");
                    return false;
                }
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
        System.out.println("11. Display menu");
        System.out.println("12. Exit");
        System.out.print("Enter your choice: ");
    }

    private void saveToFile(String filename) {
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

    private void loadFromFile(String filename) {
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

    private boolean checkIfInventoryIsEmpty(String message) {
        if (inventoryItems.isEmpty()) {
            System.out.println(message);
            System.out.println("You can add an item by selecting option '1'.");
            return true;
        }
        return false;
    }

    private boolean checkIfOrdersAreEmpty() {
        if (orders.isEmpty()) {
            System.out.println("No orders currently exist.");
            System.out.println("Please create an order first by selecting option '5'.");
            return true;
        }
        return false;
    }

}
