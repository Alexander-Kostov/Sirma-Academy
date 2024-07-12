package Solid.Single_Responsibility.Invoice.Database;

public class MongoDB implements Database{
    @Override
    public boolean save(Object obj) {
        System.out.println("Saved to MongoDB");
        return true;
    }
}
