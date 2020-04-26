package ztp.lista4;

import ztp.lista4.model.Book;
import ztp.lista4.model.Bookshelf;
import ztp.lista4.model.Room;
import ztp.lista4.utils.BookFinder;
import ztp.lista4.utils.DataFiller;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataFiller dataFiller = new DataFiller();
        List<Room> rooms = dataFiller.generateRandomRooms(3, 3, 4);
//        System.out.println(rooms);

        BookFinder bookFinder = new BookFinder();

        List<Book> booksWithMoreThan500Pages = bookFinder.findBooksWithNumberOfPagesBiggerThan(rooms, 500);
        System.out.println("Książki z liczbą stron większa niż 500:");
        System.out.println(booksWithMoreThan500Pages);
        System.out.println();

        Bookshelf exampleBookShelf = dataFiller.generateRandomBookshelf(0, 10);
        List<String> authorsOfBooksFromBookshelf = bookFinder.findAllAuthorsOfBooksOnBookshelf(exampleBookShelf);
        System.out.println("Autorzy książek na danej półce:");
        System.out.println(authorsOfBooksFromBookshelf);
        System.out.println();

        int[] bookshelvesSize = new int[] {3, 7, 4, 2, 5, 10, 2, 3, 1, 5};
        List<Room> roomsWithBookshelvesOfDifferentSize =
                dataFiller.generateRandomRooms(bookshelvesSize);
        Room roomWithBiggestNumberOfBooks =
                bookFinder.findRoomWithBiggestNumberOfBooks(roomsWithBookshelvesOfDifferentSize);
        System.out.println("Pokój z największą ilością książek:");
        System.out.println(roomWithBiggestNumberOfBooks);
        System.out.println();

        Room roomWithSmallestNumberOfBooks =
                bookFinder.findRoomWithSmallestNumberOfBooks(roomsWithBookshelvesOfDifferentSize);
        System.out.println("Pokój z najmniejszą ilością książek:");
        System.out.println(roomWithSmallestNumberOfBooks);
        System.out.println();

        int booksWithSoftback = bookFinder.findNumberOfBooksWithSpecificBackType(rooms, Book.BackType.SOFTBACK);
        int booksWithHardback = bookFinder.findNumberOfBooksWithSpecificBackType(rooms, Book.BackType.HARDBACK);
        System.out.print("Liczba książek z miękką okładką: ");
        System.out.println(booksWithSoftback);
        System.out.print("Liczba książek z twardą okładką: ");
        System.out.println(booksWithHardback);
        System.out.println();

    }
}
