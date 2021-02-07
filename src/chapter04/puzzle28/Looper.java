package chapter04.puzzle28;

public class Looper {

    public static void main(String[] args) {

        /*

        It looks as though it should run for only two iterations,
        but it can be made to loop indefinitely by taking advantage of the overflow behavior illustrated in Puzzle 26.
         */
        int start = Integer.MAX_VALUE - 1;
        for (int i = start; i <= start + 1; i++) {
            System.out.println(i);
        }

        // Integer.MAX_VALUE) is 2147483647
        // so Integer.MAX_VALUE-1 = 2147483647 - 1 = 2147483646


        // 2147483646 <= 2147483647 TRUE
        // 2147483646 + 1 = 2147483647
        // 2147483647 <=2147483647 TRUE

        // 2147483647 + 1 = IS NOT 2147483648 it is overflow
        //  2147483647 + 1  = -2147483648
        // so  -2147483648 <= 2147483647

        // hence , it is infinite loop!
    }
}
