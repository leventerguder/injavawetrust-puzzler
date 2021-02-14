# Puzzle 50: Not Your Type

This puzzle tests your understanding of Java's two classiest operators: instanceof and cast. 
What does each of the following three programs do?

<pre>
public class Type1 {
    public static void main(String[] args) {
        String s = null;
        System.out.println(s instanceof String);
    }
}

</pre>

<pre>
public class Type2 {
    public static void main(String[] args) {
        System.out.println(new Type2() instanceof String);
    }
}

</pre>

<pre>
public class Type3 {
    public static void main(String args[]) {
        Type3 t3 = (Type3) new Object();
    }
}

</pre>


The first program, Type1, illustrates the behavior of the instanceof operator when applied to a null object reference. 
Although null is a subtype of every reference type, the instanceof operator is defined to return false when its left operand is null. 

The second program, Type2, illustrates the behavior of the instanceof operator when testing an instance of one class 
to see whether it is an instance of an unrelated class

The program fails to compile because the instanceof operator requires that if both operands are class types, 
one must be a subtype of the other [JLS 15.20.2, 15.16, 5.5]. Neither Type2 nor String is a subtype of the other, 
so the instanceof test results in a compile-time error. This error helps alert you to 
instanceof tests that probably don't do what you want

Like the instanceof operation, if both types in a cast operation are class types, 
one must be a subtype of the other. Although it is obvious to us that this cast will fail, the type 
system is not powerful enough to know that the run-time type of the expression new Object() cannot be a subtype of Type3. 
Therefore, the program throws a ClassCastException at run time.

In summary, the first program illustrates a useful corner case in the run-time behavior of instanceof. 
The second program illustrates a useful corner case in its compile-time behavior. 
The third program illustrates a corner case in the behavior of the cast operator where the compiler fails to 
save you from your folly, and the VM is left to take up the slack at run time.
