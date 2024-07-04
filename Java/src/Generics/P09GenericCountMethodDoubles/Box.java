package Generics.P09GenericCountMethodDoubles;

class Box<T extends Comparable<T>> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.getClass().getName() + ": " + value;
    }
}
