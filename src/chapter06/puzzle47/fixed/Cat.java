package chapter06.puzzle47.fixed;

public class Cat {

    private static int meowCounter;

    public Cat() {
    }

    public static int meowCount() {
        return meowCounter;
    }

    public void meow() {
        meowCounter++;
    }
}
