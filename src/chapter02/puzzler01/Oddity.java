package chapter02.puzzler01;

public class Oddity {

    /*
     the isOdd method fails for all negative odd values.
     It returns false when invoked on any negative value, whether even or odd.
     */
    public static boolean isOdd(int i) {
        return i % 2 == 1;
    }

    /*
    The problem is easy to fix. Simply compare i % 2 to 0 rather than to 1
     */
    public static boolean isOddFixed(int i) {
        return i % 2 != 0;
    }

    /*
    If you are using the isOdd method in a performance-critical setting,
    you would be better off using the bitwise AND operator (&) in place of the remainder operator:
     */
    public static boolean isOddFixedWithBetterPerformance(int i) {
        return (i & 1) != 0;
    }


    public static void main(String[] args) {

        System.out.println("IsOdd:" + isOdd(5));
        System.out.println("IsOdd:" + isOdd(10));
        System.out.println("IsOdd:" + isOdd(-5)); // wrong result!
        System.out.println("IsOdd:" + isOdd(-10));
        System.out.println();

        System.out.println("isOddFixed:" + isOddFixed(5));
        System.out.println("isOddFixed:" + isOddFixed(10));
        System.out.println("isOddFixed:" + isOddFixed(-5));
        System.out.println("isOddFixed:" + isOddFixed(-10));
        System.out.println();

        System.out.println("isOddFixedWithBetterPerformance:" + isOddFixedWithBetterPerformance(5));
        System.out.println("isOddFixedWithBetterPerformance:" + isOddFixedWithBetterPerformance(10));
        System.out.println("isOddFixedWithBetterPerformance:" + isOddFixedWithBetterPerformance(-5));
        System.out.println("isOddFixedWithBetterPerformance:" + isOddFixedWithBetterPerformance(-10));

    }
}
