package Solid.Single_Responsibility.Invoice.Repository;

import Solid.Single_Responsibility.Invoice.Database.Database;

public class InvoiceRepository implements Repository{

    private Database database;

    public InvoiceRepository(Database database) {
        this.database = database;
    }

    @Override
    public boolean save(Object obj) {
        return this.database.save(obj);
    }
}
