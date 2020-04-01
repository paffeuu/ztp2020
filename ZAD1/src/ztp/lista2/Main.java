package ztp.lista2;

import ztp.lista2.model.*;
import ztp.lista2.utils.Container;
import ztp.lista2.utils.Display;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        zad11();
        zad12();
    }

    private static void zad11() {
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

//        display.showEmployeeData(personContainer);
//        display.showPersonData(employeeContainer);
    }

    private static void zad12() {
        Container<Dog> dogContainer = new Container<>();
        Dog dog = new Dog("Reksio");
        dogContainer.addObject(dog);

        Container<Cat> catContainer = new Container<>();
        Cat cat = new Cat(15);
        catContainer.addObject(cat);

        try {
            Dog dog2 = dogContainer.createNewObject("Saba");
            System.out.println(dog2 + " successfully created.");
            dogContainer.addObject(dog2);
            Cat cat2 = catContainer.createNewObject(5);
            System.out.println(cat2 + " successfully created.");
            catContainer.addObject(cat2);

            Dog[] dogsArray = dogContainer.getObjectsAsArray();
            System.out.println("Array of dogs:");
            Arrays.stream(dogsArray)
                    .forEach(System.out::println);

            Cat[] catsArray = catContainer.getObjectsAsArray();
            System.out.println("Array of cats:");
            Arrays.stream(catsArray)
                    .forEach(System.out::println);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
