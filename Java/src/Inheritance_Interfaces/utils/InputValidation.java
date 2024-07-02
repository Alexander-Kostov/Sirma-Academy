package Inheritance_Interfaces.utils;

import Inheritance_Interfaces.management.MenuOperations;

public class InputValidation {

    public boolean validateItem(String itemData, String category) {
        if (itemData == null || itemData.isEmpty()) {
            System.out.println("Invalid data. Please enter the following data: Name, Category, Price, Quantity");
            MenuOperations.displayMenu();
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

    public boolean validateCreditCardInputParameters(String input) {
        String[] data = input.split(", ");
        if (data.length != 4) {
            System.out.println("Invalid number of parameters. Please try again");
            return false;
        }
        return true;
    }
}
