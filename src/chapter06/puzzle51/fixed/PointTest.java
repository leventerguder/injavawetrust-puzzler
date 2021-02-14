package chapter06.puzzle51.fixed;

public class PointTest {

    public static void main(String[] args) {
        System.out.println(new ColorPoint(4, 2, "purple"));
    }

    /*
        Although lazy initialization fixes the problem, it is a bad idea to have one value class extend another,
        adding a field that affects equals comparisons.
     */

    /*
        To summarize, you must never call an overridable method from a constructor under any circumstances.
        The resulting circularities in instance initialization can be fatal.
        The solution to this problem is lazy initialization [EJ Items 13, 48].
     */

}
