package Solid.Liskov_Substitution.Engine;

public class Main {
    public static void main(String[] args) {
        Engine electricEngine = new ElectricEngine();
        Engine traditionalEngine = new TraditionalEngine();

        electricEngine.start();
        traditionalEngine.start();
    }
}
