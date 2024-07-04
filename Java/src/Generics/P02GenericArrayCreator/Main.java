package Generics.P02GenericArrayCreator;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] integers = ArrayCreator.create(5, 42);
        System.out.println(Arrays.toString(integers));

        String[] hellos = ArrayCreator.create(String.class, 3, "hello");
        System.out.println(Arrays.toString(hellos));
    }
}
