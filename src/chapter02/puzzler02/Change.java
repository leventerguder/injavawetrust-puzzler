package chapter02.puzzler02;

import java.math.BigDecimal;

public class Change {

    public static void main(String args[]) {

        /*
        The problem is that the number 1.1 can't be represented exactly as a double,
        so it is represented by the closest double value. The program subtracts this value from 2.
        Unfortunately, the result of this calculation is not the closest double value to 0.9.
         */
        System.out.println(2.00 - 1.10);

        // Poor solution - still uses binary floating-point!
        System.out.printf("%.2f%n", 2.00 - 1.10);

        /*
        One way to solve the problem is to use an integral type, such as int or long, and to perform the computation in cents.
        If you go this route, make sure the integral type is large enough to represent all the values you will use in your program.
        */
        System.out.println((200 - 110) + " cents");

        System.out.println(new BigDecimal("2.00").subtract(new BigDecimal("1.10")));
        /*
        Always use the BigDecimal(String) constructor, never BigDecimal(double)
         */
        System.out.println(new BigDecimal(2.00).subtract(new BigDecimal(1.10)));

    }

}

