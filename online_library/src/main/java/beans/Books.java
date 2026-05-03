package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.BookService;

import java.io.Serializable;
import java.util.ArrayList;

@Named("books")
@SessionScoped
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    private static ArrayList<Book> bookList = initBooks();

    private String searchedAuthor;

    private ArrayList<Book> filteredBooks = new ArrayList<>();

    private Book newBook = new Book();

    @Inject
    private BookService bookService;

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        Books.bookList = bookList;
    }

    public String getSearchedAuthor() {
        return searchedAuthor;
    }

    public void setSearchedAuthor(String searchedAuthor) {
        this.searchedAuthor = searchedAuthor;
    }

    public ArrayList<Book> getFilteredBooks() {
        return filteredBooks;
    }

    public void setFilteredBooks(ArrayList<Book> filteredBooks) {
        this.filteredBooks = filteredBooks;
    }

    public Book getNewBook() {
        return newBook;
    }

    public void setNewBook(Book newBook) {
        this.newBook = newBook;
    }

    public String filterByAuthor() {
        filteredBooks = bookService.filterByAuthor(bookList, searchedAuthor);

        return "filteredBooks?faces-redirect=true";
    }

    public String addBook() {
        bookService.addBook(bookList, newBook);

        newBook = new Book();

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
                "Сатирична творба, представяща образа на Бай Ганьо и неговото поведение в България и Европа."
        ));

        books.add(new Book(
                "Тютюн",
                "Димитър Димов",
                "Роман",
                760,
                "tutun.jpg",
                "Роман за обществени, лични и икономически конфликти в България преди Втората световна война."
        ));

        books.add(new Book(
                "Железният светилник",
                "Димитър Талев",
                "Исторически роман",
                496,
                "jelezniqt_svetilnik.jpg",
                "Исторически роман за българското възраждане и борбата за духовна независимост."
        ));

        return books;
    }
}