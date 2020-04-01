package ztp.lista2;

import ztp.lista2.model.Employee;
import ztp.lista2.model.Manager;
import ztp.lista2.model.Person;
import ztp.lista2.utils.Container;
import ztp.lista2.utils.Display;

public class Main {
    public static void main(String[] args) {
        Display display = new Display();

        Container<Person> personContainer = new Container<>();
        Person person = new Person("Paweł", "Kowański");
        personContainer.addObject(person);

        Container<Employee> employeeContainer = new Container<>();
        Employee employee = new Employee("Janusz", "Kowalski", "Microsoft", "IT", 3);
        employeeContainer.addObject(employee);

        Container<Manager> managerContainer = new Container<>();
        Manager manager = new Manager("Marian", "Nowak", "Apple", "Finance", 10, 5);
        managerContainer.addObject(manager);

        display.showPersonData(personContainer);
        display.showEmployeeData(employeeContainer);
        display.showManagerData(managerContainer);

        manager.givePromotion("CEO");
        manager.givePromotion(false);
        manager.givePromotion();

        ((Employee) manager).givePromotion(true);
        ((Employee) manager).givePromotion();

        ((Person) manager).givePromotion();

        display.showEmployeeData(personContainer);
        display.showPersonData(employeeContainer);
    }
}
