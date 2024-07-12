package Solid.Interface_Segregation.Machine;

public class Scanner implements ScanMachine{
    @Override
    public void scan() {
        System.out.println("This is a scan machine");
    }
}
