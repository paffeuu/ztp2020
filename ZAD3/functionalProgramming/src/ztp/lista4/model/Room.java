package ztp.lista4.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private List<Bookshelf> bookshelves;

    public Room(String name) {
        this.name = name;
        this.bookshelves = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bookshelf> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(List<Bookshelf> bookshelves) {
        this.bookshelves = bookshelves;
    }

    public void addBookshelf(Bookshelf bookshelf) {
        this.bookshelves.add(bookshelf);
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", bookshelves=\n" + bookshelves +
                "}\n";
    }
}
