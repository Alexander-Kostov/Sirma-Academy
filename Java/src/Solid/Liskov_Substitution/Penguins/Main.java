package Solid.Liskov_Substitution.Penguins;

public class Main {
    public static void main(String[] args) {
        Bird penguin = new Penguin();
        penguin.makeSound();

        Bird sparrow = new Sparrow();
        sparrow.makeSound();
    }
}
