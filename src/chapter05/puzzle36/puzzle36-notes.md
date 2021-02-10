# Puzzle 36: Indecision

What does it print? Is it even legal?

<pre>
public class Indecisive {

    public static void main(String[] args) {
        System.out.println(decision());
    }

    static boolean decision() {

        try {
            return true;
        } finally {
            return false;
        }
    }
}

</pre>

If you tried it, you found that it compiles without error and prints false. Why?

The reason is that in a try-finally statement, the finally block is always executed when control leaves 
the try block [JLS 14.20.2]. 

This is true whether the try block completes normally or abruptly. 
Abrupt completion of a statement or block occurs when it throws an exception, 
executes a break or continue to an enclosing statement, or executes a return from the method as in this program. 
These are called abrupt completions because they prevent the program from executing the next statement in sequence.


The program tries to return true but finally it returns false.

In summary, every finally block should complete normally, barring an unchecked exception. 
Never exit a finally block with a return, break, continue, or tHRow, and never allow a checked exception to propagate 
out of a finally block.

For language designers, finally blocks should perhaps be required to complete normally in the absence of 
unchecked exceptions. Toward this end, a TRy-finally construct would require that the finally block can 
complete normally [JLS 14.21]. A return, break, or continue statement that transfers control out of a 
finally block would be disallowed, as would any statement that could cause a checked exception to propagate out 
of the finally block.
