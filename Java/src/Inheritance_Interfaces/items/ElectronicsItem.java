package Inheritance_Interfaces.items;

import java.time.LocalDate;

public class ElectronicsItem extends InventoryItem {
    private LocalDate warranty;
    private boolean broken;

    public ElectronicsItem(String name, String category, double price, int quantity) {
        super(name, category, price, quantity);
        super.setBreakable(true);
        this.broken = false;
        this.warranty = LocalDate.now().plusYears(1);
    }

    public LocalDate getWarranty() {
        return warranty;
    }

    public void setWarranty(LocalDate warranty) {
        this.warranty = warranty;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }
}
