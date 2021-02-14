package chapter06.puzzle51;

public class PointTest {

    public static void main(String[] args) {
        System.out.println(new ColorPoint(4, 2, "purple"));
    }

    /*
    If you ran the program, you found that it prints [4,2]:null. What is the matter with the program?
    The program suffers from a problem with the order of instance initialization.
     */

    /*
     Circular class initialization is a necessary evil, but circular instance initialization can and should always be avoided.
     */

    /*
    The problem arises whenever a constructor calls a method that has been overridden in its subclass.
    A method invoked in this way always runs before the instance has been initialized,
    when its declared fields still have their default values. To avoid this problem,
    never call overridable methods from constructors, either directly or indirectly [EJ Item 15].
     */
}
