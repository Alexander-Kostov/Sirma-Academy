package Generics.P03GenericScale;

public class Main {
    public static void main(String[] args) {
        Scale<String> stringScale = new Scale<>("Alex", "George");
        Scale<Integer> integerScale = new Scale<>(5, 10);
        Scale<Integer> integerScale1 = new Scale<>(5, 5);

        System.out.println(stringScale.getHeavier());
        System.out.println(integerScale.getHeavier());

        System.out.println(integerScale1.getHeavier());
    }
}
