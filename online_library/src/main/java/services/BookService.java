package services;

import beans.Book;
import jakarta.ejb.Stateless;

import java.util.ArrayList;

@Stateless
public class BookService {

    public ArrayList<Book> filterByAuthor(ArrayList<Book> bookList, String searchedAuthor) {
        ArrayList<Book> filteredBooks = new ArrayList<>();

        if (searchedAuthor == null || searchedAuthor.trim().isEmpty()) {
            filteredBooks.addAll(bookList);
            return filteredBooks;
        }

        for (Book book : bookList) {
            if (book.getAuthor().toLowerCase().contains(searchedAuthor.toLowerCase().trim())) {
                filteredBooks.add(book);
            }
        }

        return filteredBooks;
    }

    public void addBook(ArrayList<Book> bookList, Book newBook) {
        bookList.add(newBook);
    }
}