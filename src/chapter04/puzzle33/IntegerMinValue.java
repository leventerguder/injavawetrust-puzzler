package chapter04.puzzle33;

public class IntegerMinValue {

    public static void main(String[] args) {

        // Integer.MIN_VALUE
        // decimal : -2147483648
        // binary : 10000000 00000000 00000000 00000000
        // hexadecimal : 0x80000000s

        System.out.println(Integer.MIN_VALUE);

        // if we negate Integer.MIN_VALUE
        // - (-2147483648) = 2147483648 , but 2147483648 is out of int range !

        int value = (int) 2147483648L; // out of int range !
        System.out.println(value); // watch out it is negative ! -2147483648

        System.out.println(Integer.MIN_VALUE == (-Integer.MIN_VALUE)); // true

        // Integer.MIN_VALUE value and - Integer.MIN_VALUE are same!

        /*
        In fact, there is exactly one such int value, and it is Integer.MIN_VALUE, or -2^31. Its hexadecimal
        representation is 0x8000000. The sign bit is 1, and all the other bits are 0. If we negate this value,
        we get 0x7fffffff + 1, which is 0x8000000, or Integer.MIN_VALUE!
         */
    }
}
