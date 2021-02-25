package chapter10.puzzle87;

public class Transitive {

    public static void main(String[] args) throws Exception {

        long x = Long.MAX_VALUE;
        double y = (double) Long.MAX_VALUE;
        long z = Long.MAX_VALUE - 1;

        System.out.print((x == y) + " "); // Imprecise!
        System.out.print((y == z) + " "); // Imprecise!
        System.out.println(x == z);        // Precise
    }
}
