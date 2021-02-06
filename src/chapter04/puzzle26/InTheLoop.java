package chapter04.puzzle26;

public class InTheLoop {

    public static final int END = Integer.MAX_VALUE;
    public static final int START = END - 100;

    public static void main(String[] args) {
        int count = 0;
        for (int i = START; i <= END; i++)
            count++;
        System.out.println(count);

        // Integer.MAX_VALUE = 2147483647

        // for int type
        // 2147483647+1 --> -2147483648 (it is negative !)
        // it's stuck in an infinite loop. never ends!

        /*

        The problem is that the loop continues as long as the loop index (i) is less than or equal to
        Integer.MAX_VALUE, but all int variables are always less than or equal to Integer.MAX_VALUE.

        It is, after all, defined to be the highest int value in existence.
        When i gets to Integer.MAX_VALUE and is incremented, it silently wraps around to Integer.MIN_VALUE.

         */

        /*
        Whenever you use an integral type, be aware of the boundary conditions.
        What happens if the value underflows or overflows?
        Often it is best to use a larger type. (The integral types are byte, char, short, int, and long.)
         */


    }
}
