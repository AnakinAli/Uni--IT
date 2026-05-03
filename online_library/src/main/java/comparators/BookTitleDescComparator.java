package comparators;

import java.util.Comparator;

import beans.Book;

public class BookTitleDescComparator implements Comparator<Book> {

    @Override
    public int compare(Book b1, Book b2) {
        return b2.getTitle().compareToIgnoreCase(b1.getTitle());
    }
}