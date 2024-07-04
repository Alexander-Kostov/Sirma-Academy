package Generics.P01JarOfT;

public class Main {
    public static void main(String[] args) {
        Jar<String> stringJar = new Jar<>();

        stringJar.addElement("First");
        stringJar.addElement("second");
        System.out.println(stringJar.removeElement());

        Jar<Integer> integerJar = new Jar<>();
        integerJar.addElement(1);
        integerJar.addElement(2);

        System.out.println(integerJar.removeElement());
    }
}
