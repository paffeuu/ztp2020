package ztp.lista1.view;

import ztp.lista1.controller.ViewListener;
import ztp.lista1.model.Model;
import ztp.lista1.model.entity.Course;
import ztp.lista1.model.entity.Student;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class View {
    private static final String MAIN_MENU = "\nMENU GŁÓWNE\nLista dostępnych opcji:\n" +
            "[1] Wyświetl listę kursów\n[2] Wyświetl listę studentów\nWybierz: ";
    private static final String COURSE_MENU[] = {
            "\nMENU KURSÓW\n",
            "Lista dostępnych opcji:\n[1] Dodaj nowy kurs\n[2] Zmień dane kursu\n[3] Usuń kurs\n" +
                    "[4] Zapisz studenta na kurs\n[5] Wypisz studenta z kursu\nWybierz: "
    };
    private static final String COURSE_CREATE[] = {"Podaj nazwę: ", "Podaj nr semestru: ", "Podaj nazwisko prowadzącego: "};
    private static final String COURSE_CREATED[] = {"Kurs ", " został utworzony."};
    private static final String CHOOSE_COURSE[] = {"\nLISTA KURSÓW:", "Wybierz kurs: "};
    private static final String COURSE_EDIT[] = {"Wpisz nowy nr semestru: ", "Wpisz nowe nazwisko prowadzącego: "};
    private static final String COURSE_EDITED[] = {"Kurs ", " zmienil dane na ", "."};
    private static final String COURSE_DELETED[] = {"Kurs ", " został usunięty."};
    private static final String COURSE_STUDENT_ADDED[] = {"Student ", " został zapisany na kurs ", "."};
    private static final String COURSE_STUDENT_REMOVED[] = {"Student ", "został usunięty z kursu ", "."};
    private static final String STUDENT_MENU[] = {
            "\nMENU STUDENTÓW\n",
            "Lista dostępnych opcji:\n[1] Dodaj nowego studenta\n[2] Zmień dane studenta\n" +
                    "[3] Usuń studenta\nWybierz: "};

    private static final String STUDENT_CREATE[] = {"Podaj imię: ", "Podaj nazwisko: ",
            "Podaj nr indeksu: ", "Wybierz płeć (K lub M): "};
    private static final String STUDENT_CREATED[] = {"Student ", " został utworzony."};
    private static final String CHOOSE_STUDENT[] = {"\nLISTA STUDENTÓW:", "Wybierz studenta: "};
    private static final String STUDENT_EDIT[] = {"Wpisz nowe imię studenta: ", "Wpisz nowe nazwisko studenta: "};
    private static final String STUDENT_EDITED[] = {"Student ", " zmienil dane na ", "."};
    private static final String STUDENT_DELETED[] = {"Student ", " został usunięty."};

    private final Model model;
    private Set<ViewListener> listeners;
    private final Scanner scanner;

    public View(Model model) {
        this.model = model;
        this.listeners = new HashSet<>();
        this.scanner = new Scanner(System.in);
    }

    public void showMainMenu() {
        System.out.print(MAIN_MENU);
        notifyActionListeners(ViewActionType.MAIN_MENU_CHOICE, null, readInput());
    }

    public void showCourseMenu() {
        System.out.println(COURSE_MENU[0]);
        showCourseList();
        System.out.print(COURSE_MENU[1]);
        notifyActionListeners(ViewActionType.COURSE_MENU_CHOICE, null, readInput());
    }

    public void showCourseCreateForm() {
        System.out.print(COURSE_CREATE[0]);
        String name = readInput();
        System.out.print(COURSE_CREATE[1]);
        String semester = readInput();
        System.out.print(COURSE_CREATE[2]);
        String teacherName = readInput();
        int semesterInt = Integer.parseInt(semester);
        Course newCourse = new Course(name, semesterInt, teacherName);
        notifyActionListeners(ViewActionType.COURSE_CREATE, newCourse, null);
    }

    public void showCourseCreated(Course course) {
        System.out.print(COURSE_CREATED[0]);
        System.out.print(course);
        System.out.print(COURSE_CREATED[1]);
    }

    public void showEditChooseCourse() {
        System.out.println(CHOOSE_COURSE[0]);
        showCourseList();
        System.out.println(CHOOSE_COURSE[1]);
        notifyActionListeners(ViewActionType.COURSE_EDIT, null, readInput());
    }

    public void showCourseEditForm(Course course) {
        System.out.println(COURSE_EDIT[0]);
        String semester = readInput();
        System.out.println(COURSE_EDIT[1]);
        String teacherName = readInput();
        int semesterInt = Integer.parseInt(semester);
        Course newCourse = new Course(course.getName(), semesterInt, teacherName);
        notifyActionListeners(ViewActionType.COURSE_EDIT, newCourse, null);
    }

    public void showCourseEdited(int oldSemester, String oldTeacherName, Course course) {
        System.out.print(COURSE_EDITED[0]);
        System.out.print("(" + oldTeacherName + " " + oldSemester + ")");
        System.out.print(COURSE_EDITED[1]);
        System.out.print(course);
        System.out.println(COURSE_EDITED[2]);
    }

    public void showDeleteChooseCourse() {
        System.out.println(CHOOSE_COURSE[0]);
        showCourseList();
        System.out.println(CHOOSE_COURSE[1]);
        notifyActionListeners(ViewActionType.COURSE_DELETE, null, readInput());
    }

    public void showCourseDeleted(Course course) {
        System.out.print(COURSE_DELETED[0]);
        System.out.print(course.toString());
        System.out.println(COURSE_DELETED[1]);
    }

    public void showAddStudentChooseCourse() {
        System.out.println(CHOOSE_COURSE[0]);
        showCourseList();
        System.out.println(CHOOSE_COURSE[1]);
        notifyActionListeners(ViewActionType.COURSE_ADD_STUDENT, null, readInput());
    }

    public void showCourseAddStudentChooseForm(Course course) {
        System.out.println(CHOOSE_STUDENT[0]);
        showStudentList(null);
        System.out.println(CHOOSE_STUDENT[1]);
        notifyActionListeners(ViewActionType.COURSE_ADD_STUDENT, course, readInput());
    }

    public void showCourseStudentAdded(Course course, Student student) {
        System.out.print(COURSE_STUDENT_ADDED[0]);
        System.out.print(student);
        System.out.print(COURSE_STUDENT_ADDED[1]);
        System.out.print(course);
        System.out.print(COURSE_STUDENT_ADDED[2]);
    }

    public void showRemoveStudentChooseCourse() {
        System.out.println(CHOOSE_COURSE[0]);
        showCourseList();
        System.out.println(CHOOSE_COURSE[1]);
        notifyActionListeners(ViewActionType.COURSE_REMOVE_STUDENT, null, readInput());
    }

    public void showCourseRemoveStudentChooseForm(Course course) {
        System.out.println(CHOOSE_STUDENT[0]);
        showStudentList(course.getSignedUpStudents());
        System.out.println(CHOOSE_STUDENT[1]);
        notifyActionListeners(ViewActionType.COURSE_REMOVE_STUDENT, course, readInput());
    }

    public void showCourseStudentRemoved(Course course, Student student) {
        System.out.print(COURSE_STUDENT_REMOVED[0]);
        System.out.print(student);
        System.out.print(COURSE_STUDENT_REMOVED[1]);
        System.out.print(course);
        System.out.print(COURSE_STUDENT_REMOVED[2]);
    }

    private void showCourseList() {
        System.out.println("Kursy:");
        List<Course> courses = model.readAllCourses();
        for (int i = 0; i < courses.size(); i++) {
            System.out.print(i + " ");
            System.out.println(courses.get(i));
        }
    }

    public void showStudentMenu() {
        System.out.println(STUDENT_MENU[0]);
        showStudentList(null);
        System.out.println(STUDENT_MENU[1]);
        notifyActionListeners(ViewActionType.STUDENT_MENU_CHOICE, null, readInput());
    }

    public void showStudentCreateForm() {
        System.out.print(STUDENT_CREATE[0]);
        String firstName = readInput();
        System.out.print(STUDENT_CREATE[1]);
        String lastName = readInput();
        System.out.print(STUDENT_CREATE[2]);
        String studentId = readInput();
        System.out.print(STUDENT_CREATE[3]);
        String gender = readInput();
        int studentIdInt = Integer.parseInt(studentId);
        char genderChar = gender.charAt(0);
        Student newStudent = new Student(firstName, lastName, studentIdInt, genderChar);
        notifyActionListeners(ViewActionType.STUDENT_CREATE, newStudent, null);
    }

    public void showStudentCreated(Student student) {
        System.out.print(STUDENT_CREATED[0]);
        System.out.print(student);
        System.out.println(STUDENT_CREATED[1]);
    }

    public void showEditChooseStudent() {
        System.out.println(CHOOSE_STUDENT[0]);
        showStudentList(null);
        System.out.println(CHOOSE_STUDENT[1]);
        notifyActionListeners(ViewActionType.STUDENT_EDIT, null, readInput());
    }

    public void showStudentEditForm(Student student) {
        System.out.println(STUDENT_EDIT[0]);
        String firstName = readInput();
        System.out.println(STUDENT_EDIT[1]);
        String lastName = readInput();
        Student newStudent = new Student(firstName, lastName, student.getStudentId(), student.getGender());
        notifyActionListeners(ViewActionType.STUDENT_EDIT, newStudent, null);
    }

    public void showDeleteChooseStudent() {
        System.out.println(CHOOSE_STUDENT[0]);
        showStudentList(null);
        System.out.println(CHOOSE_STUDENT[1]);
        notifyActionListeners(ViewActionType.STUDENT_DELETE, null, readInput());
    }

    public void showStudentEdited(String oldFirstName, String oldLastName, Student student) {
        System.out.print(STUDENT_EDITED[0]);
        System.out.print(oldFirstName + " " + oldLastName);
        System.out.print(STUDENT_EDITED[1]);
        System.out.print(student);
        System.out.println(STUDENT_EDITED[2]);
    }

    public void showStudentDeleted(Student student) {
        System.out.print(STUDENT_DELETED[0]);
        System.out.print(student.toString());
        System.out.println(STUDENT_DELETED[1]);
    }

    private void showStudentList(List<Student> students) {
        System.out.println("Studenci:");
        if (students == null) {
            students = model.readAllStudents();
        }
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
