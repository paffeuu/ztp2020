package ztp.lista1.view;

import ztp.lista1.controller.ViewListener;
import ztp.lista1.model.Model;
import ztp.lista1.model.entity.Course;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class View {
    private final static String MAIN_MENU = "MENU GŁÓWNE\nLista dostępnych opcji:\n" +
            "[1] Wyświetl listę kursów\n[2] Wyświetl listę studentów\nWybierz: ";
    private final static String COURSE_MENU[] = {
            "MENU KURSÓW\nKursy:\n",
            "Lista dostępnych opcji:\n[1] Dodaj nowy kurs\n[2] Zmień dane kursu\n[3] Usuń kurs\n" +
                    "[4] Zapisz studenta na kurs\n[5] Wypisz studenta z kursu\nWybierz: "
    };

    private final static String COURSE_CHOICE = "Podaj nr kursu: ";

    private final Model model;
    private Set<ViewListener> listeners;
    private final Scanner scanner;
    private Pattern numberPattern;

    public View(Model model) {
        this.model = model;
        this.listeners = new HashSet<>();
        this.scanner = new Scanner(System.in);
        this.numberPattern = Pattern.compile("\\d+");
    }

    public void showMainMenu() {
        System.out.print(MAIN_MENU);
        notifyActionListeners(ViewActionType.MAIN_MENU_CHOICE, null, readInput());
    }

    public void showCourseMenu() {
        System.out.println(COURSE_MENU[0]);
        System.out.println();
        System.out.print(COURSE_MENU[1]);
        notifyActionListeners(ViewActionType.COURSE_MENU_CHOICE, null, readInput());
    }

    public void showCourseChoice(ViewActionType actionType) {
        System.out.print(COURSE_CHOICE);
        notifyActionListeners(actionType, chooseCourseFromTheList(), readInput());
    }

    public void showStudentMenu() {
        System.out.println("MENU STUDENTÓW");
        System.out.println("Studenci:");
        System.out.println();
        System.out.println("Lista dostępnych opcji:");
        System.out.println("[1] Dodaj nowego studenta");
        System.out.println("[2] Zmień dane studenta");
        System.out.println("[3] Usuń studenta");
        System.out.println("Wybierz: ");
    }

    public void showIncorrectInputMessage() {
        System.out.println("Nie wybrałeś żadnej opcji z listy. Spróbuj jeszcze raz...\n");
    }

    private String readInput() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }

    private Object chooseCourseFromTheList() {
        String input = readInput();
        if (numberPattern.matcher(input).matches()) {
            int number = Integer.parseInt(input);
            Course course = model.readCourse(number);
            if (course != null) {
                return course;
            }
        }
        return -1;
    }

    public void addActionListener(ViewListener newViewListener) {
        listeners.add(newViewListener);
    }

    private void notifyActionListeners(ViewActionType viewActionType, Object object, String data) {
        this.listeners.forEach(viewListener -> viewListener.actionPerformed(viewActionType, object, data));
    }


}
