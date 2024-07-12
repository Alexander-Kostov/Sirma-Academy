package Solid.Single_Responsibility.Invoice.Printer;

import Solid.Single_Responsibility.Invoice.Model.Invoice;

public class ConsolePrinter implements Printer{
    @Override
    public void print(Invoice invoice) {
        System.out.println("The console printer prints: ");
        System.out.println(invoice);
    }
}
