package Solid.Single_Responsibility.Book.Repository;

import Solid.Single_Responsibility.Book.Database.Database;

public class BookRepository implements Repository{
    private Database database;

    public BookRepository(Database database) {
        this.database = database;
    }
    @Override
    public boolean save(Object obj) {
        this.database.save(obj);
        return false;
    }
}
