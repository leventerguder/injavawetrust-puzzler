package chapter02.puzzle03;

public class LongDivision {

    public static void main(String[] args) {
        /*
        The problem is that the computation of the constant MICROS_PER_DAY does overflow.
        Although the result of the computation fits in a long with room to spare, it doesn't fit in an int.
         */

        // overflow , it should be 864000000000 but the value is ; 500654080
        final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY); // result is 5 , it is wrong !
    }
}
