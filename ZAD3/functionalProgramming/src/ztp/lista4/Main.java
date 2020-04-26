package ztp.lista4;

import ztp.lista4.model.Room;
import ztp.lista4.utils.DataFiller;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataFiller dataFiller = new DataFiller();
        List<Room> rooms = dataFiller.generateRandomRooms(3, 3, 4);
        System.out.println(rooms);
    }
}
