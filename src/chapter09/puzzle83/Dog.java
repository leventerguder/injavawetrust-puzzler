package chapter09.puzzle83;

public class Dog extends Exception {

    public static final Dog INSTANCE = new Dog();

    private Dog() {
    }

    public String toString() {
        return "Woof";
    }
}
