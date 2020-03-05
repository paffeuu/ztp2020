package ztp;

import ztp.lista1.controller.Controller;
import ztp.lista1.model.Model;
import ztp.lista1.view.View;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);
        controller.startApplication();
    }
}
