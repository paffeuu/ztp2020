package ztp.lista2.utils;

import ztp.lista2.model.Employee;
import ztp.lista2.model.Manager;
import ztp.lista2.model.Person;

public class Display {
    public void showPersonData(Container<Person> container) {
        container.getObjects().forEach(System.out::println);
    }

    public void showEmployeeData(Container<Employee> container) {
        container.getObjects().forEach(System.out::println);
    }

    public void showManagerData(Container<Manager> container) {
        container.getObjects().forEach(System.out::println);
    }
}
