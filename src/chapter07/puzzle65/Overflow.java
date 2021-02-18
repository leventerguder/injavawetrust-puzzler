package chapter07.puzzle65;

public class Overflow {

    public static void main(String[] args) {
        /*
        When you subtract two int or long values, the result can overflow, in which case it will have the wrong sign.
         */
        int x = -2000000000;
        int z = 2000000000;
        System.out.println(x - z);

        /*
        Clearly, x is less than z, yet the program prints 294967296, which is positive.
        Given that this comparison idiom is broken, why is it used so commonly? Because it works most of the time.
        It breaks only if the numbers to which it is applied differ by more than Integer.MAX_VALUE.
        This means that for many applications, failures won't be observed in practice.
        Worse, they may be observed infrequently enough that the bug will never get found and fixed.
         */
    }
}
