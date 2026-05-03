package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import comparators.BookAuthorComparator;
import comparators.BookPagesAscComparator;
import comparators.BookPagesDescComparator;
import comparators.BookTitleAscComparator;
import comparators.BookTitleDescComparator;

@Named("books")
@SessionScoped
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    private static ArrayList<Book> bookList = initBooks();

    private String sortType;

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        Books.bookList = bookList;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String sortBooks() {
        if (sortType == null) {
            return "index?faces-redirect=true";
        }

        switch (sortType) {
            case "author":
                Collections.sort(bookList, new BookAuthorComparator());
                break;

            case "titleAsc":
                Collections.sort(bookList, new BookTitleAscComparator());
                break;

            case "titleDesc":
                Collections.sort(bookList, new BookTitleDescComparator());
                break;

            case "pagesAsc":
                Collections.sort(bookList, new BookPagesAscComparator());
                break;

            case "pagesDesc":
                Collections.sort(bookList, new BookPagesDescComparator());
                break;

            default:
                break;
        }

        return "index?faces-redirect=true";
    }

    private static ArrayList<Book> initBooks() {
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(
                "Под игото",
                "Иван Вазов",
                "Роман",
                544,
                "pod_igoto.jpg",
                "Класически български роман, описващ живота на българите преди Априлското въстание."
        ));

        books.add(new Book(
                "Бай Ганьо",
                "Алеко Константинов",
                "Сатира",
                192,
                "bai_ganyo.jpg",
                "Сатирична творба, която представя образа на Бай Ганьо и неговото поведение в България и Европа."
        ));

        books.add(new Book(
                "Тютюн",
                "Димитър Димов",
                "Роман",
                640,
                "tyutyun.jpg",
                "Роман за съдбата на семейства и личности, свързани с тютюневата индустрия."
        ));

        books.add(new Book(
                "Железният светилник",
                "Димитър Талев",
                "Исторически роман",
                496,
                "jelezniyat_svetilnik.jpg",
                "Исторически роман за българското Възраждане и борбата за духовна независимост."
        ));

        return books;
    }
}