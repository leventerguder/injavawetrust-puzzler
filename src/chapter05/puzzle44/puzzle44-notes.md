# Puzzle 44: Cutting Class

Consider these two classes:

<pre>
public class Strange1 {
    public static void main(String[] args) {
        try {
            Missing m = new Missing();
        } catch (NoClassDefFoundError ex) {
            System.out.println("Got it!");
        }
    }
}

</pre>

<pre>
public class Strange2 {
    public static void main(String[] args) {
        Missing m;
        try {
            m = new Missing();
        } catch (NoClassDefFoundError ex) {
            System.out.println("Got it!");
        }
    } 
}

</pre>

Both Strange1 and Strange2 use this class:

<pre>
class Missing {
    Missing() { }
}
</pre>

If you were to compile all three classes and then delete the file Missing.class before running Strange1 and Strange2, 
you'd find that the two programs behave differently. One throws an uncaught NoClassDefFoundError,
whereas the other prints Got it! Which is which, and how can you explain the difference in behavior?


The Strange1 program mentions the missing type only within its try block, 
so you might expect it to catch the NoClassDefFoundError and print Got it! 
The Strange2 program, on the other hand, declares a variable of the missing type outside the try block, 
so you might expect the NoClassDefFoundError generated there to be uncaught. If you tried running the programs,
you saw exactly the opposite behavior: Strange1 tHRows an uncaught NoClassDefFoundError, and Strange2 prints Got it!
What could explain this strange behavior?


If you look to the Java language specification to find out where the NoClassDefFoundError should be thrown, 
you don't get much guidance. It says that the error may be thrown "at any point in the program that 
(directly or indirectly) uses the type" [JLS 12.2.1]. When the VM invokes the main method of Strange1 or Strange2,
the program is using class Missing indirectly, so either program would be within its 
rights to throw the error at this point.

If you compare the bytecode for Strange1 and Strange2, you'll find them nearly identical. Aside from the class name, 
the only difference is the mapping of the catch parameter ex to a VM local variable. 
Although the details of which program variables are assigned to which VM variables can vary from compiler to compiler,
they are unlikely to vary much for programs as simple as these. 
Here is the code for Strange1.main as displayed by javap -c Strange1:

In classes Strange1 and Strange2, the local variable m happens to be stored in VM variable 1. 
Both versions of main also have a join point, where the flow of control from two different places converge. 
The join point is instruction 20, which is the instruction to return from main.


To write a program that can detect when a class is missing, use reflection 
to refer to the class rather than the usual language constructs [EJ Item 35].


In summary, do not depend on catching NoClassDefFoundError. 
The language specification carefully describes when class initialization occurs [JLS 12.4.1], 
but class loading is far less predictable. More generally, it is rarely appropriate to catch Error or its subclasses. 
These exceptions are reserved for failures from which recovery is not feasible.
