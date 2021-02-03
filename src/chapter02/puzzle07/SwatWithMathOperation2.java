package chapter02.puzzle07;

public class SwatWithMathOperation2 {

    public static void main(String[] args) {

        int x = 13;
        int y = 7;

        x = x * y;
        y = x / y;
        x = x / y;

        System.out.println("After Swap");
        System.out.println("x : " + x);
        System.out.println("y : " + y);

        /*
         The multiplication and division based approach doesn't work if one of the numbers is 0 as the product
         becomes 0 irrespective of the other number.
         */

        /*
        Both Arithmetic solutions may cause an arithmetic overflow. If x and y are too large,
        addition and multiplication may go out of integer range.
         */
    }
}
