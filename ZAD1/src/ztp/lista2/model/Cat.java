package ztp.lista2.model;

public class Cat {
    private int age;

    public Cat(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "age=" + age +
                '}';
    }
}
