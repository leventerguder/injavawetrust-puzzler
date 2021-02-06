package chapter04.puzzle26;

public class InTheLoopFixed {

    public static final int END = Integer.MAX_VALUE;
    public static final int START = END - 100;

    public static void main(String[] args) {
        int count = 0;

        /*
        If you need a loop that iterates near the boundaries of the int values,
        you are better off using a long variable as the loop index.
        Simply changing the type of the loop index from int to long solves the problem,
        causing the program to print 101 as expected:
         */
        for (long i = START; i <= END; i++)
            count++;
        System.out.println(count);
    }
}
