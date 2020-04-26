package ztp.lista4.model;

public class Book {
    private String author;
    private String title;
    private int numberOfPages;

    public Book(String author, String title, int numberOfPages) {
        this.author = author;
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Book(Book book) {
        this.author = book.author;
        this.title = book.title;
        this.numberOfPages = book.numberOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "\t\t\t\tBook{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                "}\n";
    }
}
