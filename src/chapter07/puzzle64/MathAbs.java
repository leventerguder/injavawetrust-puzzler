package chapter07.puzzle64;

public class MathAbs {

    public static void main(String[] args) {
        System.out.println(Math.abs(-1));
        System.out.println(Math.abs(Integer.MIN_VALUE));
        System.out.println(Math.abs(Integer.MIN_VALUE) % 3);

        /*
        This method is named a bit deceptively. It nearly always returns the absolute value of its argument,
        but in one case, it does not. The documentation says,
        "If the argument is equal to the value of Integer.MIN_VALUE, the result is that same value, which is negative."
         */
    }
}
