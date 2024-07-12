package Solid.Single_Responsibility.Book;

import Solid.Single_Responsibility.Book.Database.MongoDB;
import Solid.Single_Responsibility.Book.Database.MySQLDatabase;
import Solid.Single_Responsibility.Book.Model.Book;
import Solid.Single_Responsibility.Book.Repository.BookRepository;

public class Main {
    public static void main(String[] args) {
        Book book = new Book("Harry Potter", "J.K Rowling");
        MongoDB mongoDB = new MongoDB();
        BookRepository bookRepository = new BookRepository(mongoDB);
        bookRepository.save(book);

        Book book2 = new Book("Clean Code", "Bob Martin");
        MySQLDatabase mySQLDatabase = new MySQLDatabase();
        BookRepository bookRepository1 = new BookRepository(mySQLDatabase);
        bookRepository1.save(book2);

    }
}
