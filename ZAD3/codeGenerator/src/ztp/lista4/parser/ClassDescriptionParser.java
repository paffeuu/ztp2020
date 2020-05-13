package ztp.lista4.parser;

import ztp.lista4.model.Attribute;
import ztp.lista4.model.ClassSpecification;
import ztp.lista4.parser.exception.ParserException;

import java.util.ArrayList;
import java.util.List;

public class ClassDescriptionParser {
    private final String[] AVAILABLE_DESIGN_PATTERNS = new String[] {
            "Singleton", "Builder"
    };

    public ClassSpecification parse(String classDescription) throws ParserException {
        //ex: "create Osoba; imie:String='Jan', nazwisko:String, rok:int; getters, setters; Singleton"
        String[] temp = classDescription.split(";");
        ClassSpecification.ClassSpecificationBuilder classSpecificationBuilder =
                new ClassSpecification.ClassSpecificationBuilder();
        if (temp.length > 4) {
            throw new ParserException("Za dużo średników.");
        }
        if (temp.length >= 1) {
            String classNameDeclaration = temp[0].trim();
            String className = parseClassNameDeclaration(classNameDeclaration);
            classSpecificationBuilder.name(className);
        }
        if (temp.length >= 2) {
            String classAttributesDeclaration = temp[1].trim();
            List<Attribute> attributes = parseClassAttributesDeclaration(classAttributesDeclaration);
            classSpecificationBuilder.attributes(attributes);
        }
        if (temp.length >= 3) {
            String classAccessDeclaration = temp[2].trim();
            boolean[] gettersAndSetters = parseClassAccessDeclaration(classAccessDeclaration);
            boolean getters = gettersAndSetters[0];
            boolean setters = gettersAndSetters[1];
            classSpecificationBuilder.getters(getters).setters(setters);
        }
        if (temp.length == 4) {
            String designPatterDeclaration = temp[3].trim();
            String designPattern = parseDesignPatterDeclaration(designPatterDeclaration);
            classSpecificationBuilder.designPattern(designPattern);
        }
        return classSpecificationBuilder.build();
    }

    private String parseClassNameDeclaration(String classNameDeclaration) throws ParserException {
        //ex: "create Osoba"
        String[] temp = classNameDeclaration.split(" ");
        String createStatement = temp[0].trim();
        if (!createStatement.equals("create")) {
            throw new ParserException("Brak słowa kluczowego 'create'.");
        }
        if (temp.length != 2 || temp[1].isEmpty() || temp[1].trim().isEmpty()) {
            throw new ParserException("Niepoprawna deklaracja nazwy klasy.");
        }
        String className = temp[1].trim();
        return temp[1].trim();
    }

    private List<Attribute> parseClassAttributesDeclaration(String classAttributesDeclaration)
            throws ParserException {
        //ex: "imie:String='Jan', nazwisko:String, rok:int"
        String[] attributes = classAttributesDeclaration.split(",");
        List<Attribute> attributeList = new ArrayList<>();
        for (int i = 0; i < attributes.length; i++) {
            //ex: "imie:String='Jan'"
            String[] temp = attributes[i].split(":");
            if (temp.length != 2) {
                throw new ParserException("Niepoprawna deklaracja atrybutu klasy.");
            }
            String attributeName = temp[0].trim();
            String attributeType;
            String attributeDefaultValue = null;
            if (temp[1].contains("=")) {
                temp = temp[1].trim().split("=");
                attributeType = temp[0].trim();
                attributeDefaultValue = temp[1].trim();
                if (!attributeType.equals("String")) {
                    attributeType = attributeType.replaceAll("'", "");
                }
            } else {
                attributeType = temp[1].trim();
            }
            if (attributeName.isEmpty() || attributeType.isEmpty()) {
                throw new ParserException("Niepoprawna deklaracja nazwy lub typu atrybutu.");
            }
            Attribute attribute = new Attribute(attributeName, attributeType, attributeDefaultValue);
            attributeList.add(attribute);
        }
        return attributeList;
    }

    private boolean[] parseClassAccessDeclaration(String classAccessDeclaration) throws ParserException {
        //ex: " getters, setters"
        String[] temp = classAccessDeclaration.split(",");
        boolean[] gettersAndSetters = new boolean[2];
        if (temp.length > 2) {
            throw new ParserException("Niepoprawna deklaracja getterów i setterów.");
        }
        String firstAccessParameter = temp[0].trim();
        if (firstAccessParameter.equals("getters")) {
            gettersAndSetters[0] = true;
        } else if (firstAccessParameter.equals("setters")) {
            gettersAndSetters[1] = true;
        } else if (!firstAccessParameter.isEmpty()){
            throw new ParserException("Niepoprawna deklaracja getterów i setterów.");
        }
        if (temp.length == 2) {
            String secondAccessParameter = temp[1].trim();
            if (secondAccessParameter.equals("getters")) {
                gettersAndSetters[0] = true;
            } else if (secondAccessParameter.equals("setters")) {
                gettersAndSetters[1] = true;
            } else if (!secondAccessParameter.trim().isEmpty()){
                throw new ParserException("Niepoprawna deklaracja getterów i setterów.");
            }
        }
        return gettersAndSetters;
    }

    private String parseDesignPatterDeclaration(String designPatterDeclaration) throws ParserException {
        //ex: " Singleton"
        String designPattern = designPatterDeclaration.trim();
        for (String availableDesignPattern : AVAILABLE_DESIGN_PATTERNS) {
            if (designPattern.equals(availableDesignPattern)) {
                return designPattern;
            }
        }
        throw new ParserException("Niepoprawna deklaracja wzorca projektowego.");
    }
}
