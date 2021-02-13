# Puzzle 48: All I Get Is Static

What does the program print?

<pre>
public class Dog {

    public static void bark() {
        System.out.print("woof ");
    }

}

</pre>

<pre>
public class Basenji extends Dog {
    public static void bark() {
    }
}
</pre>


<pre>
public class Bark {

    public static void main(String[] args) {

        Dog woofer = new Dog();
        Dog nipper = new Basenji();
        woofer.bark();
        nipper.bark();
    }

}

</pre>

On casual inspection, it would appear that this program should just print woof. After all, Basenji extends Dog and 
defines its bark method to do nothing. The main method invokes the bark method, first on woofer the Dog and again 
on nipper the Basenji. Basenjis don't bark, but apparently this one does. 
If you ran the program, you found that it prints woof woof. What is the matter with poor Nipper?

The problem is that bark is a static method, and there is no dynamic dispatch on static methods [JLS 15.12.4.4]. 
When a program calls a static method, the method to be invoked is selected at compile time, 
based on the compile-time type of the qualifier, which is the name we give to the part of the method 
invocation expression to the left of the dot.


The confusion is compounded by the appearance of overriding. 
The bark method in Basenji has the same signature as the one in Dog. 
That is the usual formula for overriding, which suggests dynamic dispatch. 
In this case, however, the methods are declared static. Static methods cannot be overridden; they can only be hidden, 
and just because you can doesn't mean you should. To avoid confusion, do not hide static methods. 
There is nothing to gain, and much to lose, from reusing the name of a superclass's static method in a subclass



In summary, qualify static methods invocations with a class name, or don't qualify them at all 
if you're invoking them from within their own class, but never qualify them with an expression. 
Also, avoid hiding static methods. Together, these guidelines help eliminate the misleading 
appearance of overriding with dynamic dispatch for static methods.
