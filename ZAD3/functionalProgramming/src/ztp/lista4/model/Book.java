package ztp.lista4.model;

public class Book {
    private String author;
    private String title;
    private int numberOfPages;
    private BackType backType;

    public Book(String author, String title, int numberOfPages, BackType backType) {
        this.author = author;
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.backType = backType;
    }

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Book(Book book) {
        this.author = book.author;
        this.title = book.title;
        this.numberOfPages = book.numberOfPages;
        this.backType = book.backType;
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

    public BackType getBackType() {
        return backType;
    }

    public void setBackType(BackType backType) {
        this.backType = backType;
    }

    @Override
    public String toString() {
        return "\t\t\t\tBook{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                "}\n";
    }

    public enum BackType {
        HARDBACK, SOFTBACK
    }
}
