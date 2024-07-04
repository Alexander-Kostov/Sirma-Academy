package Generics.P10CustomList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    CustomList<String> customList = new CustomList<>();

        while (true) {
        String command = scanner.nextLine();
        if (command.equals("end")) {
            break;
        }

        String[] parts = command.split(" ");
        String action = parts[0];

        switch (action) {
            case "Add":
                customList.add(parts[1]);
                break;
            case "Remove":
                int indexToRemove = Integer.parseInt(parts[1]);
                customList.remove(indexToRemove);
                break;
            case "Contains":
                System.out.println(customList.contains(parts[1]));
                break;
            case "Swap":
                int index1 = Integer.parseInt(parts[1]);
                int index2 = Integer.parseInt(parts[2]);
                customList.swap(index1, index2);
                break;
            case "Greater":
                System.out.println(customList.countGreaterThan(parts[1]));
                break;
            case "Max":
                System.out.println(customList.getMax());
                break;
            case "Min":
                System.out.println(customList.getMin());
                break;
            case "Print":
                customList.print();
                break;
            default:
                System.out.println("Invalid command");
                break;
        }
    }

        scanner.close();
    }
}
