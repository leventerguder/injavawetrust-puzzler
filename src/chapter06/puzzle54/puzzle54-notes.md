# Puzzle 54: Null and Void

Here is yet another variant on the classic Hello World program. What does this one do?

<pre>

public class Null {

    public static void greet() {
        System.out.println("Hello world!");
    }

    public static void main(String[] args) {
        ((Null) null).greet();
    }

}

</pre>


This program looks as though it ought to throw a NullPointerException. 
The main method invokes the greet method on the constant null, and you can't invoke a method on null, can you? 
Well, sometimes you can. If you ran the program, you found that it prints "Hello world!"

This program looks as though it ought to throw a NullPointerException. The main method invokes the greet method 
on the constant null, and you can't invoke a method on null, can you? Well, sometimes you can. If you ran the program, 
you found that it prints "Hello world!"


The key to understanding this puzzle is that Null.greet is a static method. As you saw in Puzzle 48, it is a bad idea 
to use an expression as the qualifier in a static method invocation, but that is exactly what this program does.

To eliminate the confusion in this program, you could invoke the greet method by using its class as a qualifier:

<pre>
public static void main(String[] args) {
    Null.greet();
}

</pre>

Better yet, you could eliminate the qualifier entirely:

<pre>
public static void main(String[] args) {
    greet();
}
</pre>


In summary, the lesson of this puzzle is exactly the same as that of Puzzle 48: Qualify static method invocations 
with a type, or don't qualify them at all. For language designers, it should not be possible to pollute the invocation 
of a static method with an expression, which serves only to confuse.
