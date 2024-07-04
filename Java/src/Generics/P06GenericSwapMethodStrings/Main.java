package Generics.P06GenericSwapMethodStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<Box<String>> boxes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            Box<String> box = new Box<>(input);
            boxes.add(box);
        }
        int first = scanner.nextInt();
        int second = scanner.nextInt();

        swap(boxes, first, second);
        for (int i = 0; i < n; i++) {
            System.out.println(boxes.get(i));
        }
    }

    public static <T> void swap(List<T> list, int firstIndex, int secondIndex) {
        T temp = list.get(firstIndex);
        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, temp);
    }
}
