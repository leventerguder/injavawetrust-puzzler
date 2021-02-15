package chapter06.puzzle52.fixed;

/*
The Cache class should be rewritten to use eager initialization.
The resulting version is obviously correct and much simpler than the original.
 */
public class CacheFixed {

    private static final int sum = computeSum();

    private static int computeSum() {
        int result = 0;
        for (int i = 0; i < 100; i++)
            result += i;
        return result;
    }

    public static int getSum() {
        return sum;
    }
}
