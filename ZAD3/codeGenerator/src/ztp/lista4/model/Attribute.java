package ztp.lista4.model;

public class Attribute {
    private final String name;
    private final String type;
    private final String defaultValue;

    public Attribute(String name, String type, String defaultValue) {
        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
