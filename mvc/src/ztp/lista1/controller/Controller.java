package ztp.lista1.controller;

import ztp.lista1.model.Model;
import ztp.lista1.model.entity.Course;
import ztp.lista1.model.entity.Student;
import ztp.lista1.view.ViewActionType;
import ztp.lista1.view.View;

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
            case STUDENT_MENU_CHOICE:
                handleStudentMenuChoice(data);
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
            default:
                view.showIncorrectInputMessage();
                view.showMainMenu();
        }
    }

    private void handleCourseMenuChoice(String menuChoice) {
        switch (menuChoice) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            default:
                view.showIncorrectInputMessage();
                view.showCourseMenu();
        }
    }

    private void handleStudentMenuChoice(String menuChoice) {
        switch (menuChoice) {
            case "1":
                view.showCreateStudentDialog();
                break;
            case "2":
                view.showEditChooseStudentDialog();
                break;
            case "3":
                view.showDeleteChooseStudentDialog();
                break;
            default:
                view.showStudentMenu();
        }
    }

    private void handleStudentDeleted(String studentNr) {
        Student student = chooseStudentFromTheList(studentNr);
        if (student == null) {
            view.showIncorrectInputMessage();
            view.showDeleteChooseStudentDialog();
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

    private Student chooseStudentFromTheList(String input) {
        if (numberPattern.matcher(input).matches()) {
            int number = Integer.parseInt(input);
            return model.readStudent(number);
        }
        return null;
    }
}
