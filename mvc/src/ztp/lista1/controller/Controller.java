package ztp.lista1.controller;

import ztp.lista1.model.Model;
import ztp.lista1.view.View;

public class Controller {
    private final Model model;
    private final View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
//        this.view.addActionListener((actionType, data) -> doNothing());
    }

//    public void doNothing() {}

    public void startApplication() {
        view.showMainView();
    }
}
