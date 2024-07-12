package Solid.Single_Responsibility.Book.Database;

public class MySQLDatabase implements Database{
    @Override
    public boolean save(Object object) {
        System.out.println("Saved in MySQL database");
        return false;
    }
}
