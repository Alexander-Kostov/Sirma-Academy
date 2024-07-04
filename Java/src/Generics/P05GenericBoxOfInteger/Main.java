package Generics.P05GenericBoxOfInteger;

import Generics.P04GenericBox.Box;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            if (checkIfInteger(input)) {
                Box<Integer> boxOfInteger = new Box<>(Integer.parseInt(input));
                System.out.println(boxOfInteger);
            } else {
                Box<String> boxOfString = new Box<>(input);
                System.out.println(boxOfString);
            }
        }
    }

    private static boolean checkIfInteger(String input) {
        try {
            int num = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
