package Inheritance_Interfaces.order;

import Inheritance_Interfaces.items.CartItem;
import Inheritance_Interfaces.items.InventoryItem;
import Inheritance_Interfaces.management.InventoryOperations;
import Inheritance_Interfaces.payment.CreditCardPayment;
import Inheritance_Interfaces.payment.PayPalPayment;
import Inheritance_Interfaces.payment.PaymentProcessor;
import Inheritance_Interfaces.utils.InputValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static Inheritance_Interfaces.management.MenuOperations.displayMenu;

public class OrderOperations {

    private final List<Order> orders = new ArrayList<>();
    private final PaymentProcessor paymentProcessor = new PaymentProcessor();
    private final Scanner scanner = new Scanner(System.in);
    private final InventoryOperations inventoryOperations;

    private final InputValidation inputValidation;

    public OrderOperations(InventoryOperations inventoryOperations, InputValidation inputValidation) {
        this.inventoryOperations = inventoryOperations;
        this.inputValidation = inputValidation;
    }

    public void placeOrder() {
        if (this.inventoryOperations.checkIfInventoryIsEmpty("You cannot make an order with an empty inventory")) {
            System.out.println("You are back to the main menu");
            return;
        }

        List<CartItem> cart = new ArrayList<>();

        while (true) {

            System.out.println("Enter the item ID to add to the cart or type 'exit' to finish:");
            System.out.println("All Items present: ");
            this.inventoryOperations.displayInventoryItems();

            InventoryItem item = inventoryOperations.findItemById();

            if (item == null) {
                break;
            } else {
                this.inventoryOperations.selectQuantity(item, cart);
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
                    if (!this.inputValidation.validateCreditCardInputParameters(input)) {
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

    public void addItemToExistingOrder() {
        if (checkIfOrdersAreEmpty() || this.inventoryOperations.checkIfInventoryIsEmpty("---------")) return;

        Order order = findOrderById();

        if (order == null) {
            return;
        }

        System.out.println("You selected the order with ID " + order.getOrderId());
        System.out.println(order);

        while (true) {
            System.out.println("Enter the item ID to add to the cart or type 'exit' to finish:");
            this.inventoryOperations.displayInventoryItems();
            InventoryItem item = this.inventoryOperations.findItemById();
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

    public void removeItemFromOrder() {
        if (checkIfOrdersAreEmpty() || this.inventoryOperations.checkIfInventoryIsEmpty("---------")) return;

        Order order = findOrderById();

        if (order == null) {
            return;
        }

        System.out.println("You selected the order with ID " + order.getOrderId());
        System.out.println(order);

        while (true) {
            System.out.println("Enter the item ID to remove from the cart or type 'exit' to finish:");
            this.inventoryOperations.displayInventoryItems();
            InventoryItem item = this.inventoryOperations.findItemById();
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

    public void showOrders() {
        if (this.orders.isEmpty()) {
            System.out.println("There are no present orders");
            return;
        }
        System.out.println("All present orders:");
        for (Order order : this.orders) {
            System.out.println(order);
        }
    }
    public boolean checkIfOrdersAreEmpty() {
        if (orders.isEmpty()) {
            System.out.println("No orders currently exist.");
            System.out.println("Please create an order first by selecting option '5'.");
            return true;
        }
        return false;
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
}
