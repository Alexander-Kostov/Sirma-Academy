package Solid.Liskov_Substitution.Penguins;

public class Sparrow extends Bird implements Flyable{
    @Override
    public void makeSound() {
        System.out.println("Sparrow noise");
    }

    @Override
    public void fly() {
        System.out.println("Flying");
    }
}
