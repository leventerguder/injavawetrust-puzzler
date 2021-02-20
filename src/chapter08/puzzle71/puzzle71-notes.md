# Puzzle 71: Import Duty

What does the program print?

<pre>
import static java.util.Arrays.toString;

public class ImportDuty {

    public static void main(String[] args) {
        printArgs(1, 2, 3, 4, 5);
    }

    static void printArgs(Object... args) {
        System.out.println(toString(args));
    }

}
</pre>



You might expect the program to print [1, 2, 3, 4, 5], and indeed it would, if only it compiled. 
Sadly, the compiler just can't seem to find the right toString method:

ImportDuty.java:9:Object.toString can't be applied to (Object[])

The first thing the compiler does when selecting a method to be invoked at run time 
is to choose the scope in which the method must be found [JLS 15.12.1]. 

The compiler chooses the smallest enclosing scope that has a method with the right name.
In our program, this scope is the class ImportDuty, which contains the toString method inherited from Object. 
This scope has no applicable method for the invocation toString(args), so the compiler must reject the program.


When one declaration shadows another, the simple name refers to the entity in the shadowing declaration. 
In this case, toString refers to the toString method from Object. 
Simply put, members that are naturally in scope take precedence over static imports. 
One consequence is that static methods with the same name as Object methods cannot be used with the static import facility.

If you are desperate to avoid qualifying Arrays.toString invocations explicitly, 
you can write your own private static forwarding method:

<pre>
private static String toString(Object[] a) {
    return Arrays.toString(a);
}
</pre>

The static import facility was intended for situations in which static members of another class are used repeatedly, 
and qualifying each use would seriously clutter a program.
In such situations, the static import facility can significantly enhance readability. 
It is far safer than implementing interfaces to inherit their constants, which you should never do [EJ Item 17]. 
Overuse of the static import facility can, however, harm readability by making the class of a static member 
unclear at the point of use. Use the static import facility sparingly and only when there is a compelling need.
