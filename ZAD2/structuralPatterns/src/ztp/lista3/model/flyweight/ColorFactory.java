package ztp.lista3.model.flyweight;

import java.util.ArrayList;
import java.util.List;

public class ColorFactory {
    public static final ColorFactory INSTANCE = new ColorFactory();

    private final List<Color> colors;

    private ColorFactory() {
        this.colors = new ArrayList<>();
    }

    public Color getColor(String colorName) {
        return colors.stream()
                .filter(color -> color.getName().equals(colorName))
                .findFirst()
                .orElseGet(() -> newColor(colorName));
    }

    private Color newColor(String colorName) {
        Color newColor = new Color(colorName);
        colors.add(newColor);
        return newColor;
    }
}
