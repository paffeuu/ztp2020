package ztp.lista1.view;

import ztp.lista1.controller.ViewListener;
import ztp.lista1.model.Model;
import ztp.lista1.model.entity.Student;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class View {
    private final static String MAIN_MENU = "\nMENU GŁÓWNE\nLista dostępnych opcji:\n" +
            "[1] Wyświetl listę kursów\n[2] Wyświetl listę studentów\nWybierz: ";
    private final static String COURSE_MENU[] = {
            "\nMENU KURSÓW\nKursy:\n",
            "Lista dostępnych opcji:\n[1] Dodaj nowy kurs\n[2] Zmień dane kursu\n[3] Usuń kurs\n" +
                    "[4] Zapisz studenta na kurs\n[5] Wypisz studenta z kursu\nWybierz: "
    };
    private final static String STUDENT_MENU[] = {
            "\nMENU STUDENTÓW\nStudenci:\n",
            "Lista dostępnych opcji:\n[1] Dodaj nowego studenta\n[2] Zmień dane studenta\n" +
                    "[3] Usuń studenta\nWybierz: "};

    private final static String CHOOSE_STUDENT[] = {"\nLISTA STUDENTÓW:", "Wybierz studenta: "};
    private final static String STUDENT_EDIT[] = {"Wpisz nowe imię studenta: ", "Wpisz nowe nazwisko studenta: "};
    private final static String STUDENT_DELETED[] = {"Student ", " został usunięty."};
    private final static String STUDENT_EDITED[] = {"Student ", " zmienil dane na ", "."};

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

    public void showStudentMenu() {
        System.out.println(STUDENT_MENU[0]);
        showStudentList();
        System.out.println(STUDENT_MENU[1]);
        notifyActionListeners(ViewActionType.STUDENT_MENU_CHOICE, null, readInput());
    }

    public void showCreateStudentDialog() {

    }

    public void showEditChooseStudentDialog() {
        System.out.println(CHOOSE_STUDENT[0]);
        showStudentList();
        System.out.println(CHOOSE_STUDENT[1]);
        notifyActionListeners(ViewActionType.STUDENT_EDIT, null, readInput());
    }

    public void showDeleteChooseStudentDialog() {
        System.out.println(CHOOSE_STUDENT[0]);
        showStudentList();
        System.out.println(CHOOSE_STUDENT[1]);
        notifyActionListeners(ViewActionType.STUDENT_DELETE, null, readInput());
    }

    public void showStudentEdit(Student student) {
        System.out.println(STUDENT_EDIT[0]);
        String firstName = readInput();
        System.out.println(STUDENT_EDIT[1]);
        String lastName = readInput();
        Student newStudent = new Student(firstName, lastName, student.getStudentId(), student.getGender());
        notifyActionListeners(ViewActionType.STUDENT_EDIT, newStudent, null);
    }

    public void showStudentDeleted(Student student) {
        System.out.print(STUDENT_DELETED[0]);
        System.out.print(student.toString());
        System.out.println(STUDENT_DELETED[1]);
    }

    public void showStudentEdited(String oldFirstName, String oldLastName, Student student) {
        System.out.print(STUDENT_EDITED[0]);
        System.out.print(oldFirstName + " " + oldLastName);
        System.out.print(STUDENT_EDITED[1]);
        System.out.print(student);
        System.out.println(STUDENT_EDITED[2]);
    }

    public void showStudentList() {
        System.out.println("Studenci:");
        List<Student> students = model.readAllStudents();
        for (int i = 0; i < students.size(); i++) {
            System.out.print(i + " ");
            System.out.println(students.get(i));
        }
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

    public void addActionListener(ViewListener newViewListener) {
        listeners.add(newViewListener);
    }

    private void notifyActionListeners(ViewActionType viewActionType, Object object, String data) {
        this.listeners.forEach(viewListener -> viewListener.actionPerformed(viewActionType, object, data));
    }


}
