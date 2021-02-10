# Puzzle 37: Exceptionally Arcane

This puzzle tests your knowledge of the rules for declaring exceptions thrown by methods and caught by catch blocks. 
What does each of the following three programs do? Don't assume that all of them compile:


<pre>
public class Arcane1 {

    public static void main(String[] args) {

        try {
            System.out.println("Hello world");
        } catch (IOException e) {
            //compile error
            System.out.println("I've never seen println fail!");
        }

    }
}
</pre>

<pre>
public class Arcane2 {

    public static void main(String[] args) {

        try {
            // If you have nothing nice to say, say nothing
        } catch (Exception e) {
            System.out.println("This can't happen");
        }

    }
}

</pre>

<pre>
interface Type1 {
    void f() throws CloneNotSupportedException;
}

interface Type2 {
    void f() throws InterruptedException;
}


interface Type3 extends Type1, Type2 {
}

public class Arcane3 implements Type3 {

    @Override
    public void f() {
        System.out.println("Hello world");
    }

    public static void main(String[] args) {
        Type3 t3 = new Arcane3();
        t3.f();
    }

}

</pre>


Arcane1, illustrates a basic principle of checked exceptions. 
It may look as though it should compile: The try clause does I/O, and the catch clause catches IOException. 
But the program does not compile because the println method isn't declared to throw any checked exceptions, 
and IOException is a checked exception. The language specification says that it is a compile-time error for 
a catch clause to catch a checked exception type E if the corresponding TRy clause can't throw an exception 
of some subtype of E [JLS 11.2.3].


By the same token, the second program, Arcane2, may look as though it shouldn't compile, but it does. 
It compiles because its sole catch clause checks for Exception. Although the JLS is not terribly clear on this point,
catch clauses that catch Exception or Throwable are legal regardless of the contents of the corresponding try clause. 
Although Arcane2 is a legal program, the contents of its catch clause will never be executed; the program prints nothing.


The third program, Arcane3, also looks as though it shouldn't compile. 
Method f is declared to throw checked exception CloneNotSupportedException in interface 
Type1 and to throw checked exception InterruptedException in interface Type2. 
Interface Type3 inherits from Type1 and Type2, so it would seem that invoking f on an object whose static type is 
Type3 could potentially throw either of these exceptions. A method must either catch each checked exception 
its body can throw, or declare that it throws the exception. The main method in Arcane3 invokes 
f on an object whose static type is Type3 but does neither of these things for CloneNotSupportedException or 
InterruptedException. Why does the program compile?



The flaw in this analysis is the assumption that Type3.
f can throw either the exception declared on Type1.
f or the one declared on Type2.f. This simply isn't true. 
Each interface limits the set of checked exceptions that method f can throw. 
The set of checked exceptions that a method can throw is the intersection of the sets of checked exceptions 
that it is declared to throw in all applicable types, not the union. As a result, the f method on an object 
whose static type is Type3 can't throw any checked exceptions at all. 
Therefore, Arcane3 compiles without error and prints Hello world.



In summary, the first program illustrates the basic requirement that catch clauses for checked exceptions 
are permitted only when the corresponding try clause can throw the exception in question. 

The second program illustrates a corner case where this requirement does not apply. 

The third program illustrates the interaction of multiple inherited throws clauses, 
which reduces rather than increases the number of exceptions that a method is permitted to throw. 
The behaviors illustrated by this puzzle don't generally cause subtle bugs, 
but they can be a bit surprising the first time you see them.
