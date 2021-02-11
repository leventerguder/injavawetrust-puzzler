package chapter05.puzzle42;

public class LoopFixed {

    public static void main(String[] args) {
        int[][] tests = {{6, 5, 4, 3, 2, 1}, {1, 2},
                {1, 2, 3}, {1, 2, 3, 4}, {1}};

        int n = 0;
        for (int[] test : tests)
            if (thirdElementIsThree(test))
                n++;

        System.out.println(n);
    }

    /*
        This operator differs from the more commonly used conditional
        AND operator (&&) in that the & operator always evaluates both of its operands,
        whereas the && operator does not evaluate its right operand if its
        left operand evaluates to false [JLS 15.23].
     */
    private static boolean thirdElementIsThree(int[] a) {
        return a.length >= 3 && a[2] == 3;
    }
}
