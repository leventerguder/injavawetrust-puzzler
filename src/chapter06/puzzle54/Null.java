package chapter06.puzzle54;

public class Null {

    public static void greet() {
        System.out.println("Hello world!");
    }

    public static void main(String[] args) {
        ((Null) null).greet();
    }

}
