package ztp.lista1;

import ztp.lista1.controller.Controller;
import ztp.lista1.model.Model;
import ztp.lista1.model.jdbc.JDBCConnector;
import ztp.lista1.view.View;

public class Main {
    public static void main(String[] args) {
        JDBCConnector connector = new JDBCConnector("jdbc:sqlite:mvc.sqlite3");


        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);
        controller.startApplication();
    }
}
