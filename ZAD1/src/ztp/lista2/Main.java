package ztp.lista2;

import ztp.lista2.model.*;
import ztp.lista2.utils.Container;
import ztp.lista2.utils.Display;
import ztp.lista2.utils.Pair;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        zad11();
        zad12a();
        zad12b();
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

    private static void zad12a() {
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

    private static void zad12b() {
        try {
            Pair<String> stringPair = new Pair<String>("a", "b");
            System.out.println(stringPair);
            Pair<String> stringPair2 = stringPair.clone();
            System.out.println(stringPair2);

            Pair<Integer> integerPair = new Pair<>(10, 20);
            System.out.println(integerPair);
            Pair<Integer> integerPair2 = integerPair.clone();
            System.out.println(integerPair2);

            Pair<Dog> dogPair = new Pair<>(new Dog("Burek"), new Dog("Reksio"));
            System.out.println(dogPair);
            Pair<Dog> dogPair2 = dogPair.clone();
            System.out.println(dogPair2);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
