package ztp.lista4.utils;

import ztp.lista4.model.Book;
import ztp.lista4.model.Bookshelf;
import ztp.lista4.model.Room;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookFinder {

    public List<Book> getAllBooks(List<Room> rooms) {
        Stream<Book> books = getBooksStream(rooms);
        return books.collect(Collectors.toList());
    }

    //filter
    public List<Book> findBooksWithNumberOfPagesBiggerThan(List<Room> rooms, int pages) {
        Stream<Book> books = getBooksStream(rooms);
        return books.filter(book -> book.getNumberOfPages() > pages)
                .sorted(Comparator.comparingInt(Book::getNumberOfPages))
                .collect(Collectors.toList());
    }

    //map
    public List<String> findAllAuthorsOfBooksOnBookshelf(Bookshelf bookshelf) {
        return bookshelf.getBooks().stream()
                .map(Book::getAuthor)
                .distinct()
                .collect(Collectors.toList());
    }

    //max
    public Room findRoomWithBiggestNumberOfBooks(List<Room> rooms) {
        return rooms.stream()
                .max(Comparator.comparingInt(this::getBooksNumberInRoom))
                .orElse(null);
    }

    //min
    public Room findRoomWithSmallestNumberOfBooks(List<Room> rooms) {
        return rooms.stream()
                .min(Comparator.comparingInt(this::getBooksNumberInRoom))
                .orElse(null);
    }

    //groupingBy
    public int findNumberOfBooksWithSpecificBackType(List<Room> rooms, Book.BackType backType) {
        Map<Book.BackType, List<Book>> bookByBackTypeMap =
                getBooksStream(rooms).collect(Collectors.groupingBy(Book::getBackType));
        return bookByBackTypeMap.get(backType).size();
    }

    private Stream<Book> getBooksStream(List<Room> rooms) {
        return rooms.stream()
                .map(Room::getBookshelves)
                .flatMap(List::stream)
                .map(Bookshelf::getBooks)
                .flatMap(List::stream);
    }

    private int getBooksNumberInRoom(Room room) {
        return (int) room.getBookshelves().stream()
                .map(Bookshelf::getBooks)
                .mapToLong(List::size)
                .sum();
    }
}
