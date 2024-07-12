package Solid.Liskov_Substitution.Engine;

public class ElectricEngine implements Engine{
    @Override
    public void start() {
        System.out.println("Electric engine starts...");
    }
}
