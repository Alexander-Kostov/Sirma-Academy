package Solid.Liskov_Substitution.Penguins;

public class Penguin extends Bird{
    @Override
    public void makeSound() {
        System.out.println("Penguin noise");
    }
}
