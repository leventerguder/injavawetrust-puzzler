package chapter02.puzzler08;

public class DosEquisFixed {
    public static void main(String[] args) {

        char x = 'X';
        char i = 0;

        System.out.print(true ? x : 0);
        System.out.print(false ? i : x);

        /*
        In summary, it is generally best to use the same type for the second and third operands in conditional expressions.
        Otherwise, you and the readers of your program must have a thorough understanding of the complex specification
        for the behavior of these expressions.
         */

    }
}
