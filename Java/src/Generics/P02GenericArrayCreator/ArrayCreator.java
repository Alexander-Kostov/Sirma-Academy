package Generics.P02GenericArrayCreator;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayCreator {

    public static <T> T[] create(int length, T item) {
        T[] array = (T[]) Array.newInstance(item.getClass(), length);
        Arrays.fill(array, item);
        return array;
    }

    // Претоварен метод за създаване на масив с дадена дължина и елемент по подразбиране, използвайки класа
    public static <T> T[] create(Class<T> clazz, int length, T item) {
        T[] array = (T[]) Array.newInstance(clazz, length);
        Arrays.fill(array, item);
        return array;
    }
}
