package Solid.Open_Closed.Logger;

public class FileLogger implements Logger{

    private String filename;

    public FileLogger(String filename) {
        this.filename = filename;
    }

    @Override
    public void log(String message) {
        System.out.println("In " + filename + " " + message);
    }
}
