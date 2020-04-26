package ztp.lista4.utils;

import ztp.lista4.model.Book;
import ztp.lista4.model.Bookshelf;
import ztp.lista4.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataFiller {
    private static final String[] ROOM_TYPES;
    private static final String[] ROOM_OWNERS;
    private static final Book[] BOOKS;
    private static final Random RANDOM;

    static {
        ROOM_TYPES  = new String[]{
                "Salon", "Sypialnia", "Gabinet", "Pokój dzienny", "Pracownia"
        };
        ROOM_OWNERS = new String[]{
                "Marka", "Tomka", "Janka", "Andrzeja", "Stasia", "Ani", "Kasi", "Agnieszki", "Moniki", "Oli"
        };
        BOOKS = new Book[]{
                new Book("Mickiewicz", "Dziady"),
                new Book("Mickiewicz", "Pan Tadeusz"),
                new Book("Sienkiewicz", "Potop"),
                new Book("Prus", "Lalka"),
                new Book("Dostojewski", "Zbrodnia i kara"),
                new Book("Herling-Grudziński", "Inny świat"),
                new Book("Gombrowicz", "Ferdydurke"),
                new Book("Wyspiański", "Wesele"),
                new Book("Reymont", "Chłopi"),
                new Book("Żeromski", "Przedwiośnie"),
                new Book("Żeromski", "Ludzie bezdomni"),
                new Book("Żeromski", "Syzyfowe prace")
        };
        RANDOM = new Random();
    }

    public List<Room> generateRandomRooms(int roomsNumber, int bookshelvesForRoom, int booksForBookshelf) {
        List<Room> rooms = new ArrayList<>(roomsNumber);
        for (int i = 0; i < roomsNumber; i++) {
            Room room = generateRandomRoom(bookshelvesForRoom, booksForBookshelf);
            rooms.add(room);
        }
        return rooms;
    }


    public Room generateRandomRoom(int bookshelvesForRoom, int booksForBookshelf) {
        int roomTypeNr = RANDOM.nextInt(ROOM_TYPES.length);
        int roomOwner = RANDOM.nextInt(ROOM_OWNERS.length);
        String roomName = ROOM_TYPES[roomTypeNr] + " " + ROOM_OWNERS[roomOwner];
        Room room = new Room(roomName);
        for (int i = 0; i < bookshelvesForRoom; i++) {
            Bookshelf bookshelf = generateRandomBookshelf(room.getBookshelves().size(), booksForBookshelf);
            room.addBookshelf(bookshelf);
        }
        return room;
    }

    public Bookshelf generateRandomBookshelf(int bookshelfNumber, int booksForBookshelf) {
        Bookshelf bookshelf = new Bookshelf(bookshelfNumber);
        for (int i = 0; i < booksForBookshelf; i++) {
            Book book = generateRandomBook();
            bookshelf.addBook(book);
        }
        return bookshelf;
    }

    public Book generateRandomBook() {
        int randomBookNr = RANDOM.nextInt(BOOKS.length);
        int randomNumberOfPages = RANDOM.nextInt(1000);
        Book book = new Book(BOOKS[randomBookNr]);
        book.setNumberOfPages(randomNumberOfPages);
        return book;
    }
}
