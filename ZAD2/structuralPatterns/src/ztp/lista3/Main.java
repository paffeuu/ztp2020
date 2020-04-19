package ztp.lista3;

import ztp.lista3.model.builder.House;
import ztp.lista3.model.flyweight.Color;
import ztp.lista3.model.flyweight.ColorFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<House> houses = new ArrayList<>();

        House house1 = new House.HouseBuilder()
                .owner("Janusz", "Kowalski")
                .dateOfBuild(2019, 12, 10)
                .houseType(House.HouseType.BLOCK_OF_FLATS)
                .color("czerwony")
                .floors(10)
                .build();
        houses.add(house1);

        House house2 = new House.HouseBuilder()
                .owner("Miłosz", "Kosowski")
                .dateOfBuild(2017, 2, 23)
                .houseType(House.HouseType.APARTMENT)
                .color("niebieski")
                .floors(2)
                .build();
        houses.add(house2);

        House house3 = new House.HouseBuilder()
                .owner("Katarzyna", "Nosińska")
                .dateOfBuild(2000, 1, 5)
                .houseType(House.HouseType.CABIN)
                .color("czerwony")
                .floors(1)
                .build();
        houses.add(house3);

        houses.forEach(System.out::println);
    }
}
