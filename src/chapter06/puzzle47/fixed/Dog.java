package chapter06.puzzle47.fixed;

public class Dog {
    private static int woofCounter;

    public Dog() {
    }

    public static int woofCount() {
        return woofCounter;
    }

    public void woof() {
        woofCounter++;
    }
}
