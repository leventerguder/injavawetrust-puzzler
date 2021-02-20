package chapter08.puzzle71;

import static java.util.Arrays.toString;

public class ImportDuty {

    public static void main(String[] args) {
        printArgs(1, 2, 3, 4, 5);
    }

    static void printArgs(Object... args) {
        // System.out.println(toString(args));         // compiler error
    }
    /*
        java.lang.Object#toString
        java.util.Arrays#toString;
     */

    /*
    When one declaration shadows another, the simple name refers to the entity in the shadowing declaration.
    In this case, toString refers to the toString method from Object.
    Simply put, members that are naturally in scope take precedence over static imports.
    One consequence is that static methods with the same name as Object methods cannot be used with the static import facility.
     */
}
