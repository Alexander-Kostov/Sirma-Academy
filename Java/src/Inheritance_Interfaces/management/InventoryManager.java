package Inheritance_Interfaces.management;

import Inheritance_Interfaces.management.InventoryItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private List<InventoryItem> items = new ArrayList<>();

    public void addItem(InventoryItem item) {
        items.add(item);
    }

    public void removeItemById(long id) {
        items.removeIf(item -> item.getId() == id);
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ItemID,Name,Category,Price,Quantity,Perishable,Breakable,Perished,Broken");
            writer.newLine();
            for (InventoryItem item : items) {
                writer.write(item.getId() + "," +
                        item.getName() + "," +
                        item.getCategory() + "," +
                        item.getPrice() + "," +
                        item.getQuantity() + "," +
                        item.isPerishable() + "," +
                        item.isBreakable() + "," +
                        item.isPerished() + "," +
                        item.isBroken());
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
                    boolean perishable = Boolean.parseBoolean(fields[5]);
                    boolean breakable = Boolean.parseBoolean(fields[6]);
                    boolean perished = Boolean.parseBoolean(fields[7]);
                    boolean broken = Boolean.parseBoolean(fields[8]);

                    InventoryItem item = new InventoryItem(name, category, breakable, perishable, price, quantity);
                    item.setItemId(itemId);
                    item.setPerished(perished);
                    item.setBroken(broken);
                    items.add(item);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading from file: " + e.getMessage());
        }
    }

    public void displayItems() {
        for (InventoryItem item : items) {
            System.out.println(item.getDetails());
        }
    }

}
