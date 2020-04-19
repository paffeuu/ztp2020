package ztp.lista3.model.flyweight;

public class Color {
    private final String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
