package Solid.Single_Responsibility.Book.Database;

public class MongoDB implements Database{
    @Override
    public boolean save(Object object) {
        System.out.println("Saved in MnogoDB database");
        return true;
    }
}
