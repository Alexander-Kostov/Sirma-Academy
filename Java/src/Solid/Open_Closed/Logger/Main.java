package Solid.Open_Closed.Logger;

public class Main {
    public static void main(String[] args) {
        ConsoleLogger consoleLogger = new ConsoleLogger();
        consoleLogger.log("Hello world");

        FileLogger fileLogger = new FileLogger("file.txt");
        fileLogger.log("Hello world");
    }
}
