package ztp.lista1.controller;

import ztp.lista1.model.Model;
import ztp.lista1.view.ViewActionType;
import ztp.lista1.view.View;

public class Controller {
    private final Model model;
    private final View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.view.addActionListener(this::handleUserInput);
    }

    private void handleUserInput(ViewActionType viewActionType, Object object, String data) {
        switch (viewActionType) {
            case MAIN_MENU_CHOICE:
                handleMainMenuChoice(data);
                break;
            case COURSE_MENU_CHOICE:
                handleCourseMenuChoice(data);
                break;
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

    public void startApplication() {
        view.showMainMenu();
    }
}
