package chapter06.puzzle52;

public class Cache {

    static {
        initializeIfNecessary();
    }

    private static int sum;

    public static int getSum() {
        initializeIfNecessary();
        return sum;
    }

    /*
    After the static block executes, the static initializer for the initialized field sets it back to false,
    completing the class initialization of Cache. Unfortunately, sum now contains the correct cached value,
    but initialized contains false:
     */
    private static boolean initialized = false;

    private static synchronized void initializeIfNecessary() {
        if (!initialized) {
            for (int i = 0; i < 100; i++)
                sum += i;
            initialized = true;
        }
    }
}
