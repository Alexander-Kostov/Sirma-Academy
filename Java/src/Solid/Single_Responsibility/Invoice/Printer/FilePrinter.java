package Solid.Single_Responsibility.Invoice.Printer;

import Solid.Single_Responsibility.Invoice.Model.Invoice;

public class FilePrinter implements Printer{

    private String filepath;

    public FilePrinter(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void print(Invoice invoice) {
        System.out.println("The File Printer prints with filepath: " + filepath + " ");
        System.out.println(invoice);
    }
}
