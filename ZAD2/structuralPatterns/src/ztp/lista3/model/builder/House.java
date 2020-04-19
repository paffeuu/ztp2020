package ztp.lista3.model.builder;

import ztp.lista3.model.flyweight.Color;
import ztp.lista3.model.flyweight.ColorFactory;

import java.time.DateTimeException;
import java.time.LocalDate;

public class House {
    private String ownerFirstName;
    private String ownerLastName;
    private final LocalDate dateOfBuild;
    private Color color;
    private final int floors;
    private final HouseType type;

    private House(HouseBuilder builder) {
        this.ownerFirstName = builder.ownerFirstName;
        this.ownerLastName = builder.ownerLastName;
        this.dateOfBuild = builder.dateOfBuild;
        this.color = builder.color;
        this.floors = builder.floors;
        this.type = builder.type;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public enum HouseType {
        CABIN, APARTMENT, HOUSE, BLOCK_OF_FLATS, TENEMENT_HOUSE
    }

    public static class HouseBuilder {
        private String ownerFirstName;
        private String ownerLastName;
        private LocalDate dateOfBuild;
        private Color color;
        private int floors;
        private HouseType type;

        public HouseBuilder owner(String firstName, String lastName) {
            this.ownerFirstName = firstName;
            this.ownerLastName = lastName;
            return this;
        }

        public HouseBuilder dateOfBuild(int year, int month, int day) {
            try {
                this.dateOfBuild = LocalDate.of(year, month, day);
                return this;
            } catch (DateTimeException e) {
                throw new HouseBuilderException("House object cannot be created! Invalid values as date input.");
            }
        }

        public HouseBuilder color(String name) {
            this.color = ColorFactory.INSTANCE.getColor(name);
            return this;
        }

        public HouseBuilder floors(int floors) {
            this.floors = floors;
            return this;
        }

        public HouseBuilder houseType(HouseType type) {
            this.type = type;
            return this;
        }

        public House build() {
            return new House(this);
        }

        public static class HouseBuilderException extends RuntimeException {
            public HouseBuilderException(String message) {
                super(message);
            }
        }
    }

    @Override
    public String toString() {
        return "House{" +
                "owner='" + ownerFirstName + " " + ownerLastName + "'" +
                ", dateOfBuild=" + dateOfBuild +
                ", color=" + color +
                ", floors=" + floors +
                ", type=" + type +
                '}';
    }
}
