package Solid.Liskov_Substitution.Engine;

public class TraditionalEngine implements Engine{
    @Override
    public void start() {
        System.out.println("Traditional engine starts...");
    }
}
