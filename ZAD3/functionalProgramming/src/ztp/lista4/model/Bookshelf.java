package ztp.lista4.model;

import java.util.ArrayList;
import java.util.List;

public class Bookshelf {
    private int numberInBookCase;
    private List<Book> books;

    public Bookshelf(int numberInBookCase) {
        this.numberInBookCase = numberInBookCase;
        this.books = new ArrayList<>();
    }

    public int getNumberInBookCase() {
        return numberInBookCase;
    }

    public void setNumberInBookCase(int numberInBookCase) {
        this.numberInBookCase = numberInBookCase;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    @Override
    public String toString() {
        return "\t\tBookshelf{" +
                "numberInBookCase=" + numberInBookCase +
                ", books=\n" + books +
                "}\n";
    }
}
