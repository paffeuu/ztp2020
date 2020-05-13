package ztp.lista4.model;

import java.util.List;

public class ClassSpecification {
    private final String name;
    private final List<Attribute> attributes;
    private final boolean getters;
    private final boolean setters;
    private final String designPattern;

    public ClassSpecification(String name, List<Attribute> attributes, boolean getters, boolean setters,
                              String designPattern) {
        this.name = name;
        this.attributes = attributes;
        this.getters = getters;
        this.setters = setters;
        this.designPattern = designPattern;
    }

    public String getName() {
        return name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public boolean isGetters() {
        return getters;
    }

    public boolean isSetters() {
        return setters;
    }

    public String getDesignPattern() {
        return designPattern;
    }

    public static class ClassSpecificationBuilder {
        private String name;
        private List<Attribute> attributes;
        private boolean getters;
        private boolean setters;
        private String designPattern;

        public ClassSpecificationBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ClassSpecificationBuilder attributes(List<Attribute> attributes) {
            this.attributes = attributes;
            return this;
        }

        public ClassSpecificationBuilder getters(boolean getters) {
            this.getters = getters;
            return this;
        }

        public ClassSpecificationBuilder setters(boolean setters) {
            this.setters = setters;
            return this;
        }

        public ClassSpecificationBuilder designPattern(String designPattern) {
            this.designPattern = designPattern;
            return this;
        }

        public ClassSpecification build() {
            return new ClassSpecification(name, attributes, getters, setters, designPattern);
        }
    }
}
