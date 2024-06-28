package Inheritance_Interfaces.items;

import Inheritance_Interfaces.management.InventoryItem;

import java.time.LocalDate;

public class ElectronicsItem extends InventoryItem {
    private LocalDate warranty;
    public ElectronicsItem(String name, String category, boolean breakable, boolean perishable, double price,
                           int quantity) {
        super(name, category, breakable, perishable, price, quantity);
        this.warranty = LocalDate.now();
    }

    public LocalDate getWarranty() {
        return warranty;
    }

    public void setWarranty(LocalDate warranty) {
        this.warranty = warranty;
    }
}
