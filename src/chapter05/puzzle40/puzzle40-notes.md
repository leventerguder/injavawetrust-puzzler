# Puzzle 40: The Reluctant Constructor

What does it print?

<pre>
public class Reluctant {

    private Reluctant internalInstance = new Reluctant();

    public Reluctant() throws Exception {
        throw new Exception("I'm not coming out");
    }

    public static void main(String[] args) {
        try {
            Reluctant r = new Reluctant();
            System.out.println("Surprise!");
        } catch (Exception ex) {
            System.out.println("I told you so");
        }
    }
}

</pre>


The main method invokes the Reluctant constructor, which throws an exception. 
You might expect the catch clause to catch this exception and print I told you so. 
A closer look at the program reveals that the Reluctant instance contains a second internal instance, 
and its constructor also throws an exception. Whichever exception gets thrown, 
it looks as though the catch clause in main should catch it, 
so it seems a safe bet that the program will print I told you so. 
But if you tried running it, you found that it does nothing of the sort: It throws a StackOverflowError. Why?


Like most programs that throw a StackOverflowError, this one contains an infinite recursion.
When you invoke a constructor, the instance variable initializers run before the body of the constructor [JLS 12.5].


In this case, the initializer for the variable internalInstance invokes the constructor recursively.
hat constructor, in turn, initializes its own internalInstance field by invoking 
the Reluctant constructor again and so on, ad infinitum.

It is not uncommon for an object to contain instances of its own type. 
This happens, for example, in linked list nodes, tree nodes, and graph nodes. 
You must initialize such contained instances carefully to avoid a StackOverflowError.


In summary, instance initializers run before constructor bodies. 
Any exceptions thrown by instance initializers propagate to constructors. 
If initializers throw checked exceptions, constructors must be declared to throw them too, 
but this should be avoided, because it is confusing. Finally, beware of infinite recursion 
when designing classes whose instances contain other instances of the same class.
