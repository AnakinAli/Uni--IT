package comparators;

import java.util.Comparator;

import beans.Book;

public class BookPagesDescComparator implements Comparator<Book> {

    @Override
    public int compare(Book b1, Book b2) {
        return Integer.compare(b2.getPages(), b1.getPages());
    }
}