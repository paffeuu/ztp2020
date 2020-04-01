package ztp.lista2.utils;

import ztp.lista2.model.Person;

import java.util.ArrayList;
import java.util.List;

public class Container<T extends Person> {
    private List<T> objects;

    public Container() {
        objects = new ArrayList<>();
    }

    public void addObject(T object) {
        objects.add(object);
    }

    public List<T> getObjects() {
        return objects;
    }
}
