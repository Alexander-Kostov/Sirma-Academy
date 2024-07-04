package Generics.P09GenericCountMethodDoubles;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        List<Box<Double>> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Double value = Double.parseDouble(scanner.nextLine());// Consume the newline
            list.add(new Box<>(value));
        }

        Double compareValue = Double.parseDouble(scanner.nextLine());
        Box<Double> compareBox = new Box<>(compareValue);

        int count = countGreaterThan(list, compareBox);

        System.out.println(count);

        scanner.close();
    }

    public static <T extends Comparable<T>> int countGreaterThan(List<Box<T>> list, Box<T> element) {
        int count = 0;
        for (Box<T> box : list) {
            if (box.getValue().compareTo(element.getValue()) > 0) {
                count++;
            }
        }
        return count;
    }
}
