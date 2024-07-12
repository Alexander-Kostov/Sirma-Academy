package Solid.Single_Responsibility.Invoice.Database;

public class MySQLDatabase implements Database{
    @Override
    public boolean save(Object obj) {
        System.out.println("Saved to MySQL database");
        return true;
    }
}
