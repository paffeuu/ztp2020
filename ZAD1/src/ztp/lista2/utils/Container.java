package ztp.lista2.utils;

import ztp.lista2.model.Person;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class Container<T> {
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

    public T createNewObject(Object parameterValue) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        if (objects.isEmpty()) {
            return null;
        }
        T object = objects.get(0);
        Class clazz = object.getClass();
        Constructor constructor = clazz.getDeclaredConstructors()[0];
        Parameter parameter = constructor.getParameters()[0];
        if (parameter.getType().getSimpleName().equals("Integer")) {
            int age = (int) parameterValue;
            return (T) constructor.newInstance(age);
        } else if (parameter.getType().getSimpleName().equals("String")) {
            String name = (String) parameterValue;
            return (T) constructor.newInstance(name);
        }
        return null;
    }

    public T[] getObjectsAsArray() {
        if (objects.isEmpty()) {
            return null;
        }
        T object = objects.get(0);
        Class clazz = object.getClass();
        T[] array = (T[])Array.newInstance(clazz, objects.size());
        for (int i = 0; i < array.length; i++) {
            array[i] = objects.get(i);
        }
        return array;
    }


}
