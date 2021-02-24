package chapter09.puzzle83;

public class DogFixed extends Exception {

    public static final DogFixed INSTANCE = new DogFixed();

    private DogFixed() {
    }

    public String toString() {
        return "Woof";
    }

    private Object readResolve() {
        // Accept no substitutes!
        return INSTANCE;
    }
}
