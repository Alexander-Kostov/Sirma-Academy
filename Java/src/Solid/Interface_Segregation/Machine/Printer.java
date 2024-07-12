package Solid.Interface_Segregation.Machine;

public class Printer implements PrintMachine {
    @Override
    public void print() {
        System.out.println("This is a print machine");
    }

}
