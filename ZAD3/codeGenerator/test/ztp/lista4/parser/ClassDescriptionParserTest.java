package ztp.lista4.parser;

import org.junit.jupiter.api.Test;
import ztp.lista4.parser.exception.ParserException;

import static org.junit.jupiter.api.Assertions.*;

class ClassDescriptionParserTest {

    @org.junit.jupiter.api.Test
    void parse() throws ParserException {
        ClassDescriptionParser parser = new ClassDescriptionParser();

        parser.parse("create Osoba; imie:String='Jan', nazwisko:String, rok:int; getters, setters; Singleton");
        parser.parse("create Osoba; imie:String='Jan', nazwisko:String, rok:int; getters, setters; Builder");
        parser.parse("create Osoba; imie:String='', nazwisko:String, rok:int; getters, setters; Singleton");
        parser.parse("create Osoba; imie:String, nazwisko:String, rok:int=23; getters, setters; Singleton");
        parser.parse("create Osoba; imie:String, nazwisko:String, rok:int; getters, setters; Singleton");
        parser.parse("create Osoba; imie:String, nazwisko:String, rok:int; setters; Singleton");
        parser.parse("create Osoba; imie:String, nazwisko:String, rok:int; getters; Singleton");
        parser.parse("create Osoba; imie:String, nazwisko:String, rok:int; ; Singleton");
        parser.parse("create Osoba; imie:String, nazwisko:String, rok:int; ;");
        parser.parse("create Osoba; imie:String, nazwisko:String, rok:int; ");
        parser.parse("create Osoba; imie:String, nazwisko:String, rok:int");
        parser.parse("create Osoba;");
        parser.parse("create Osoba");

        assertThrows(ParserException.class, () -> parser.parse(
                "crete Osoba; imie:String='Jan', nazwisko:String, rok:int; getters, setters; Singleton"));
        assertThrows(ParserException.class, () -> parser.parse(
                "createOsoba; imie:String='Jan', nazwisko:String, rok:int; getters, setters; Singleton"));
        assertThrows(ParserException.class, () -> parser.parse(
                "create Osoba a; imie:String='Jan', nazwisko:String, rok:int; getters, setters; Singleton"));
        assertThrows(ParserException.class, () -> parser.parse(
                "create Osoba; :String='Jan', nazwisko:String, rok:int; getters, setters; Singleton"));
        assertThrows(ParserException.class, () -> parser.parse(
                "create Osoba; imie:='Jan', nazwisko:String, rok:int; getters, setters; Singleton"));
        assertThrows(ParserException.class, () -> parser.parse(
                "create Osoba; imie:String='Jan', nazwisko:String, rok:; getters, setters; Singleton"));
        assertThrows(ParserException.class, () -> parser.parse(
                "create Osoba; imie:String='Jan', nazwisko:String, rok:int; gettaers, setters; Singleton"));
        assertThrows(ParserException.class, () -> parser.parse(
                "create Osoba; imie:String='Jan', nazwisko:String, rok:int; getters, settexrs; Singleton"));
        assertThrows(ParserException.class, () -> parser.parse(
                "create Osoba; imie:String='Jan', nazwisko:String, rok:int; getters, x; Singleton"));
        assertThrows(ParserException.class, () -> parser.parse(
                "create Osoba; imie:String='Jan', nazwisko:String, rok:int; getters, setters; Singlekton"));
        assertThrows(ParserException.class, () -> parser.parse(
                "create Osoba; imie:String='Jan', nazwisko:String, rok:int; getters, setters; Buildker"));

    }
}