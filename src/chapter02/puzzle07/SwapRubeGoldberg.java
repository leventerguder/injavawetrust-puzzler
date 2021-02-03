package chapter02.puzzle07;

public class SwapRubeGoldberg {

    public static void main(String[] args) {

        // Rube Goldberg would approve, but don't ever do this!

        int x = 13;
        int y = 7;

        y = (x ^= (y ^= x)) ^ y;

        System.out.println("After Swap");
        System.out.println("x : " +x );
        System.out.println("y : " +y );
    }
}
