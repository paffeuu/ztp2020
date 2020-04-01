package ztp.lista2.utils;

public class Pair<T> implements Cloneable {
    private T firstElem;
    private T secondElem;

    public Pair(T firstElem, T secondElem) {
        this.firstElem = firstElem;
        this.secondElem = secondElem;
    }

    public Pair<T> clone() throws CloneNotSupportedException {
        return new Pair<>(firstElem, secondElem);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "firstElem=" + firstElem +
                ", secondElem=" + secondElem +
                '}';
    }
}
