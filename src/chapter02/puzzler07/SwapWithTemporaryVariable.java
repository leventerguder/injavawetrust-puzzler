package chapter02.puzzler07;

public class SwapWithTemporaryVariable {

    public static void main(String[] args) {

        int x = 10;
        int y = 20;

        int tmp = x;
        x = y;
        y = tmp;

        System.out.println("After Swap");
        System.out.println("x : " + x);
        System.out.println("y : " + y);

    }

}
