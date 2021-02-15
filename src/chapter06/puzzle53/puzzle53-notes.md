# Puzzle 53: Do Your Thing

Now it's your turn to write some code. Suppose that you have a library class called 
Thing whose sole constructor takes an int parameter:

<pre>

public class Thing {
    public Thing(int i) { ... }
    ...
}

</pre>


A Thing instance provides no way to get the value of its constructor parameter. 
Because Thing is a library class, you have no access to its internals, and you can't modify it.


Suppose that you want to write a subclass called MyThing, with a constructor that computes the parameter 
to the superclass constructor by invoking the method SomeOtherClass.func(). 
The value returned by this method changes unpredictably from call to call. 
Finally, suppose that you want to store the value that was passed to the superclass constructor 
in a final instance field of the subclass for future use. This is the code that you'd naturally write:

<pre>

public class MyThing extends Thing {
    private final int arg;
    public MyThing() {
        super(arg = SomeOtherClass.func());
        ...
}
... }

</pre>

Unfortunately, it isn't legal.
How can you rewrite MyThing to achieve the desired effect? 
The MyThing() constructor must be thread-safe: Multiple threads may invoke it concurrently.


This solution uses an alternate constructor invocation [JLS 8.8.7.1]. 
This feature allows one constructor in a class to chain to another constructor in the same class. 
In this case, MyThing() chains to the private constructor MyThing(int), which performs 
the required instance initialization. Within the private constructor, the value of the expression 
SomeOtherClass.func() has been captured in the parameter i and can be stored in the final field param after the superclass 
constructor returns.
The Private Constructor Capture idiom illustrated by the solution to this puzzle is a useful pattern to 
add to your bag of tricks. We've seen some genuinely ugly code that could have been avoided with this pattern.
