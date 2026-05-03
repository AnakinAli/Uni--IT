package comparators;

import java.util.Comparator;

import beans.Book;

public class BookPagesAscComparator implements Comparator<Book> {

    @Override
    public int compare(Book b1, Book b2) {
        return Integer.compare(b1.getPages(), b2.getPages());
    }
}