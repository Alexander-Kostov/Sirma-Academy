package Generics.P05GenericBoxOfInteger;

public class Box<T> {
    private T item;

    public Box(T item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return this.item.getClass().getName() + ": " + item;
    }
}

