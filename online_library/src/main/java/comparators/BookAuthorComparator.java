package comparators;

import java.util.Comparator;

import beans.Book;

public class BookAuthorComparator implements Comparator<Book> {

    @Override
    public int compare(Book b1, Book b2) {
        return b1.getAuthor().compareToIgnoreCase(b2.getAuthor());
    }
}