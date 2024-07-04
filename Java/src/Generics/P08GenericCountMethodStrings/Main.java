package Generics.P08GenericCountMethodStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();

        List<Box<String>> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String value = scanner.nextLine();
            list.add(new Box<>(value));
        }

        String compareValue = scanner.nextLine();
        Box<String> compareBox = new Box<>(compareValue);

        int count = countGreaterThan(list, compareBox);

        System.out.println(count);

        scanner.close();
    }

    public static <T extends Comparable<T>> int countGreaterThan(List<Box<T>> list, Box<T> element) {
        int count = 0;
        for (Box<T> box : list) {
            if (box.compareTo(element) > 0) {
                count++;
            }
        }
        return count;
    }
}
