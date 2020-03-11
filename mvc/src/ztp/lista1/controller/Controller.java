package ztp.lista1.controller;

import ztp.lista1.model.Model;
import ztp.lista1.model.entity.Course;
import ztp.lista1.model.entity.Student;
import ztp.lista1.view.ViewActionType;
import ztp.lista1.view.View;

import java.util.List;
import java.util.regex.Pattern;

public class Controller {
    private final Model model;
    private final View view;

    private Pattern numberPattern;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.view.addActionListener(this::handleUserInput);
        this.numberPattern = Pattern.compile("\\d+");
    }

    private void handleUserInput(ViewActionType viewActionType, Object object, String data) {
        switch (viewActionType) {
            case MAIN_MENU_CHOICE:
                handleMainMenuChoice(data);
                break;
            case COURSE_MENU_CHOICE:
                handleCourseMenuChoice(data);
                break;
            case COURSE_CREATE:
                handleCourseCreated((Course) object);
                break;
            case COURSE_EDIT:
                if (object == null) {
                    handleCourseEdit(data);
                } else {
                    handleCourseEdited((Course) object);
                }
                break;
            case COURSE_DELETE:
                handleCourseDeleted(data);
                break;
            case COURSE_ADD_STUDENT:
                if (object == null) {
                    handleCourseAddStudent(data);
                } else {
                    handleCourseStudentAdded((Course) object, data);
                }
                break;
            case COURSE_REMOVE_STUDENT:
                if (object == null) {
                    handleCourseRemoveStudent(data);
                } else {
                    handleCourseStudentRemoved((Course) object, data);
                }
                break;
            case STUDENT_MENU_CHOICE:
                handleStudentMenuChoice(data);
                break;
            case STUDENT_CREATE:
                handleStudentCreated((Student) object);
                break;
            case STUDENT_EDIT:
                if (object == null) {
                    handleStudentEdit(data);
                } else {
                    handleStudentEdited((Student) object);
                }
                break;
            case STUDENT_DELETE:
                handleStudentDeleted(data);
        }
    }

    private void handleMainMenuChoice(String menuChoice) {
        switch (menuChoice) {
            case "1":
                view.showCourseMenu();
                break;
            case "2":
                view.showStudentMenu();
                break;
            case "0":
                break;
            default:
                view.showIncorrectInputMessage();
                view.showMainMenu();
        }
    }

    private void handleCourseMenuChoice(String menuChoice) {
        switch (menuChoice) {
            case "1":
                view.showCourseCreateForm();
                break;
            case "2":
                view.showEditChooseCourse();
                break;
            case "3":
                view.showDeleteChooseCourse();
                break;
            case "4":
                view.showAddStudentChooseCourse();
                break;
            case "5":
                view.showRemoveStudentChooseCourse();
                break;
            case "0":
                view.showMainMenu();
                break;
            default:
                view.showIncorrectInputMessage();
                view.showCourseMenu();
        }
    }

    private void handleStudentMenuChoice(String menuChoice) {
        switch (menuChoice) {
            case "1":
                view.showStudentCreateForm();
                break;
            case "2":
                view.showEditChooseStudent();
                break;
            case "3":
                view.showDeleteChooseStudent();
                break;
            case "0":
                view.showMainMenu();
                break;
            default:
                view.showStudentMenu();
        }
    }

    private void handleCourseCreated(Course course) {
        if (model.createCourse(course)) {
            view.showCourseCreated(course);
        } else {
            view.showCourseNotCreated(course);
        }

        view.showMainMenu();
    }

    private void handleCourseEdit(String courseNr) {
        Course course = chooseCourseFromTheList(courseNr);
        if (course == null) {
            view.showIncorrectInputMessage();
            view.showEditChooseCourse();
        } else {
            view.showCourseEditForm(course);
        }
    }

    private void handleCourseEdited(Course course) {
        Course originalCourse = model.findCourseByName(course.getName());
        int oldSemester = originalCourse.getSemester();
        String oldTeacherName = originalCourse.getTeacherName();
        model.setCourseSemesterAndTeacherName(originalCourse, course.getSemester(), course.getTeacherName());
        view.showCourseEdited(oldSemester, oldTeacherName, originalCourse);
        view.showMainMenu();
    }

    private void handleCourseDeleted(String courseNr) {
        Course course = chooseCourseFromTheList(courseNr);
        if (course == null) {
            view.showIncorrectInputMessage();
            view.showDeleteChooseCourse();
        } else {
            model.removeCourse(course);
            view.showCourseDeleted(course);
            view.showMainMenu();
        }
    }

    private void handleCourseAddStudent(String courseNr) {
        Course course = chooseCourseFromTheList(courseNr);
        if (course == null) {
            view.showIncorrectInputMessage();
            view.showAddStudentChooseCourse();
        } else {
            view.showCourseAddStudentChooseForm(course);
        }
    }

    private void handleCourseStudentAdded(Course course, String studentNr) {
        Student student = chooseStudentFromTheList(studentNr, null);
        if (student == null) {
            view.showIncorrectInputMessage();
            view.showCourseAddStudentChooseForm(course);
        } else {
            model.addStudentToCourse(course, student);
            view.showCourseStudentAdded(course, student);
            view.showMainMenu();
        }
    }

    private void handleCourseRemoveStudent(String courseNr) {
        Course course = chooseCourseFromTheList(courseNr);
        if (course == null) {
            view.showIncorrectInputMessage();
            view.showRemoveStudentChooseCourse();
        } else {
            view.showCourseRemoveStudentChooseForm(course);
        }
    }

    private void handleCourseStudentRemoved(Course course, String studentNr) {
        Student student = chooseStudentFromTheList(studentNr, course.getSignedUpStudents());
        if (student == null) {
            view.showIncorrectInputMessage();
            view.showCourseRemoveStudentChooseForm(course);
        } else {
            model.removeStudentFromCourse(course, student);
            view.showCourseStudentRemoved(course, student);
            view.showMainMenu();
        }
    }

    private void handleStudentCreated(Student student) {
        model.createStudent(student);
        view.showStudentCreated(student);
        view.showMainMenu();
    }

    private void handleStudentEdit(String studentNr) {
        Student student = chooseStudentFromTheList(studentNr, null);
        if (student == null) {
            view.showIncorrectInputMessage();
            view.showEditChooseStudent();
        } else {
            view.showStudentEditForm(student);
        }
    }

    private void handleStudentEdited(Student student) {
        Student originalStudent = model.findStudentByStudentId(student.getStudentId());
        String oldFirstName = originalStudent.getFirstName();
        String oldLastName = originalStudent.getLastName();
        model.setStudentFirstNameAndLastName(originalStudent, student.getFirstName(), student.getLastName());
        view.showStudentEdited(oldFirstName, oldLastName, originalStudent);
        view.showMainMenu();
    }

    private void handleStudentDeleted(String studentNr) {
        Student student = chooseStudentFromTheList(studentNr, null);
        if (student == null) {
            view.showIncorrectInputMessage();
            view.showDeleteChooseStudent();
        } else {
            model.removeStudent(student);
            view.showStudentDeleted(student);
            view.showMainMenu();
        }
    }

    public void startApplication() {
        view.showMainMenu();
    }


    private Course chooseCourseFromTheList(String input) {
        if (numberPattern.matcher(input).matches()) {
            int number = Integer.parseInt(input);
            return model.readCourse(number);
        }
        return null;
    }

    private Student chooseStudentFromTheList(String input, List<Student> students) {
        if (students == null) {
            students = model.readAllStudents();
        }
        if (numberPattern.matcher(input).matches()) {
            int number = Integer.parseInt(input);
            if (number < students.size()) {
                return students.get(number);
            }
        }
        return null;
    }
}
