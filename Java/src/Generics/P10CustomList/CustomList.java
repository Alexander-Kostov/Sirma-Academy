package Generics.P10CustomList;

import java.util.ArrayList;
import java.util.List;

public class CustomList<T extends Comparable<T>> {
    private List<T> elements;

    public CustomList() {
        elements = new ArrayList<>();
    }

    public void add(T element) {
        elements.add(element);
    }

    public T remove(int index) {
        return elements.remove(index);
    }

    public boolean contains(T element) {
        return elements.contains(element);
    }

    public void swap(int index1, int index2) {
        T temp = elements.get(index1);
        elements.set(index1, elements.get(index2));
        elements.set(index2, temp);
    }

    public int countGreaterThan(T element) {
        int count = 0;
        for (T el : elements) {
            if (el.compareTo(element) > 0) {
                count++;
            }
        }
        return count;
    }

    public T getMax() {
        if (elements.isEmpty()) {
            return null;
        }
        T max = elements.get(0);
        for (T el : elements) {
            if (el.compareTo(max) > 0) {
                max = el;
            }
        }
        return max;
    }

    public T getMin() {
        if (elements.isEmpty()) {
            return null;
        }
        T min = elements.get(0);
        for (T el : elements) {
            if (el.compareTo(min) < 0) {
                min = el;
            }
        }
        return min;
    }

    public void print() {
        for (T element : elements) {
            System.out.println(element);
        }
    }
}
