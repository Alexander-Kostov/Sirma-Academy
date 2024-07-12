package Solid.Interface_Segregation.Machine;

public class FaxMachineImpl implements FaxMachine{
    @Override
    public void fax() {
        System.out.println("This is a fax machine");
    }
}
