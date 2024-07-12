package Solid.Single_Responsibility.Book.Model;

public class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // ... other properties
    @Override
    public String toString() {
        return title + " by " + author;
    }
}