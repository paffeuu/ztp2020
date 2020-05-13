package ztp.lista4;

import ztp.lista4.model.ClassSpecification;
import ztp.lista4.parser.ClassDescriptionParser;
import ztp.lista4.parser.exception.ParserException;

public class Main {
    public static void main(String[] args) throws ParserException {
        ClassDescriptionParser parser = new ClassDescriptionParser();
        ClassSpecification cs1 = parser.parse("");
    }
}
