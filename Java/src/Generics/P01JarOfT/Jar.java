package Generics.P01JarOfT;

import java.util.ArrayDeque;

public class Jar<T> {

    private ArrayDeque<T> elements;

    public Jar() {
        elements = new ArrayDeque<>();
    }

    public void addElement(T element) {
        this.elements.push(element);
    }

    public T removeElement() {
        return  this.elements.pop();
    }

}
