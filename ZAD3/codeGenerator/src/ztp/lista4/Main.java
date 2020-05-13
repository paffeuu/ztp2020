package ztp.lista4;

import ztp.lista4.generator.ClassGenerator;
import ztp.lista4.model.ClassSpecification;
import ztp.lista4.output.ClassConsoleLogger;
import ztp.lista4.output.ClassFileGenerator;
import ztp.lista4.parser.ClassDescriptionParser;
import ztp.lista4.parser.exception.ParserException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParserException {
        ClassDescriptionParser parser = new ClassDescriptionParser();
        ClassGenerator generator = new ClassGenerator();
        ClassFileGenerator fileGenerator = new ClassFileGenerator();
        ClassConsoleLogger fallbackConsoleLogger = new ClassConsoleLogger();


        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Witaj w generatorze kodu źródłowego klasy w języku Java!");
                System.out.println("Wpisz deklarację klasy wg wzoru:");
                System.out.println("create <nazwa_klasy>; <nazwa_atrybutu>:<typ_atrybutu>[='wartosc_atrybutu'], ...; [getters, setters]; [Singleton/Builder]");
                System.out.println("Przykładowa deklaracja:");
                System.out.println("create Osoba; imie:String='Jan', nazwisko:String, rok:int; getters, setters; Singleton");
                System.out.println("Aby wyjść wpisz 'exit'.");
                System.out.print("...: ");

                String input = scanner.nextLine();
                if (input.equals("exit")) {
                    break;
                }
                String className = null;
                String generatedClassSource = null;
                try {
                    ClassSpecification parsedSpecification = parser.parse(input);
                    generatedClassSource = generator.generateClass(parsedSpecification);
                    System.out.println("Klasa została poprawnie wygenerowana.");
                    className = parsedSpecification.getName();
                    fileGenerator.generateClassFile(className, generatedClassSource);
                    System.out.println("Klasa została poprawnie zapisana do pliku.");
                } catch (ParserException e) {
                    System.out.println("Niepoprawna deklaracja klasy. Spróbuj jeszcze raz...");
                } catch (IOException e) {
                    System.out.println("Wystąpił błąd! Klasa nie mogła zostać zapisana do pliku.");
                    System.out.println("Źródła wygenerowanej klasy zostaną wypisane do konsoli:");
                    fallbackConsoleLogger.logClassInConsole(className, generatedClassSource);
                }
                System.out.println("\n\n");
            }
        }
    }
}
