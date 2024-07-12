package Solid.Single_Responsibility.Invoice;

import Solid.Single_Responsibility.Invoice.Database.MongoDB;
import Solid.Single_Responsibility.Invoice.Database.MySQLDatabase;
import Solid.Single_Responsibility.Invoice.Model.Invoice;
import Solid.Single_Responsibility.Invoice.Printer.ConsolePrinter;
import Solid.Single_Responsibility.Invoice.Printer.FilePrinter;
import Solid.Single_Responsibility.Invoice.Repository.InvoiceRepository;

public class Main {
    public static void main(String[] args) {
        Invoice invoice = new Invoice(1500.25, "Peter");

        ConsolePrinter consolePrinter = new ConsolePrinter();
        consolePrinter.print(invoice);

        FilePrinter filePrinter = new FilePrinter("invoice-folder");
        filePrinter.print(invoice);

        MongoDB mongoDB = new MongoDB();
        InvoiceRepository invoiceRepository = new InvoiceRepository(mongoDB);
        invoiceRepository.save(invoice);

        MySQLDatabase mySQLDatabase = new MySQLDatabase();
        InvoiceRepository invoiceRepository1 = new InvoiceRepository(mySQLDatabase);
        invoiceRepository1.save(invoice);
    }
}
