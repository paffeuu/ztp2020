package ztp.lista4.generator;

import ztp.lista4.model.Attribute;
import ztp.lista4.model.ClassSpecification;

public class ClassGenerator {
    private StringBuilder sb;
    private ClassSpecification specification;

    public String generateClass(ClassSpecification specification) {
        StringBuilder stringBuilder = new StringBuilder();
        this.sb = stringBuilder;
        this.specification = specification;

        appendInitialClassDeclaration();
        appendAttributes();
        appendSingleInstanceAttribute();
        appendPrivateConstructor();
        appendGetInstanceMethod();
        appendGetters();
        appendSetters();
        appendEndOfClass();

        this.sb = null;
        this.specification = null;
        return stringBuilder.toString();
    }

    private void appendInitialClassDeclaration() {
        //ex: 'public class Osoba {\n'
        sb.append("public class ")
                .append(specification.getName())
                .append(" {")
                .append("\n");
    }

    private void appendAttributes() {
        //ex: 'private String imie = "Jan";\nprivate int rok;\n\n
        for (Attribute attribute : specification.getAttributes()) {
            sb.append("\t");
            sb.append("private ")
                    .append(attribute.getType())
                    .append(" ")
                    .append(attribute.getName());
            if (attribute.getDefaultValue() != null) {
                sb.append(" = ")
                        .append(attribute.getDefaultValue());
            }
            sb.append(";\n");
        }
        sb.append("\n");
    }

    private void appendSingleInstanceAttribute() {
        if (specification.getDesignPattern().equals("Singleton")) {
            sb.append("\t")
                    .append("private static final ")
                    .append(specification.getName())
                    .append(" INSTANCE = new ")
                    .append(specification.getName())
                    .append("();")
                    .append("\n");
        }
    }

    private void appendPrivateConstructor() {
        if (specification.getDesignPattern().equals("Singleton")) {
            sb.append("\t")
                    .append("private ")
                    .append(specification.getName())
                    .append("() {}")
                    .append("\n\n");
        }
    }

    private void appendGetInstanceMethod() {
        if (specification.getDesignPattern().equals("Singleton")) {
            sb.append("\t")
                    .append("public static ")
                    .append(specification.getName())
                    .append(" getInstance() {")
                    .append("\n")
                    .append("\t\t")
                    .append("return INSTANCE;")
                    .append("\n")
                    .append("\t")
                    .append("}")
                    .append("\n\n");
        }
    }

    private void appendGetters() {
        if (specification.isGetters()) {
            for (Attribute attribute : specification.getAttributes()) {
                sb.append("\t")
                        .append("public ")
                        .append(attribute.getType())
                        .append(" get")
                        .append(Character.toUpperCase(attribute.getName().charAt(0)))
                        .append(attribute.getName().substring(1))
                        .append("() {")
                        .append("\n")
                        .append("\t\t")
                        .append("return ")
                        .append(attribute.getName())
                        .append(";")
                        .append("\n")
                        .append("\t")
                        .append("}")
                        .append("\n\n");
            }
        }
    }

    private void appendSetters() {
        if (specification.isSetters()) {
            for (Attribute attribute : specification.getAttributes()) {
                sb.append("\t")
                        .append("public void set")
                        .append(Character.toUpperCase(attribute.getName().charAt(0)))
                        .append(attribute.getName().substring(1))
                        .append("(")
                        .append(attribute.getType())
                        .append(" ")
                        .append(attribute.getName())
                        .append(") {")
                        .append("\n")
                        .append("\t\t")
                        .append("this.")
                        .append(attribute.getName())
                        .append(" = ")
                        .append(attribute.getName())
                        .append(";")
                        .append("\n")
                        .append("\t")
                        .append("}")
                        .append("\n\n");
            }
        }
    }

    private void appendEndOfClass() {
        sb.append("}");
    }

}
