package Generics.P08GenericCountMethodStrings;

public class Box<T extends Comparable<T>> implements Comparable<Box<T>> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public int compareTo(Box<T> other) {
        return this.value.compareTo(other.value);
    }

    @Override
    public String toString() {
        return value.getClass().getName() + ": " + value;
    }
}
