# Puzzle 39: Hello, Goodbye

What does it print?
<pre>
public class HelloGoodbye {

    public static void main(String[] args) {

        try {
            System.out.println("Hello world");
            System.exit(0);
        } finally {
            System.out.println("Goodbye world");
        }
    }
}

</pre>

The program contains two println statements: one in a try block and the other in the corresponding finally block. 
The try block executes its println and finishes execution prematurely by calling System.exit. 
At this point, you might expect control to transfer to the finally block. 
If you tried the program, though, you found that it never can say goodbye: 
It prints only Hello world. Doesn't this violate the principle explained in Puzzle 36?

It is true that a finally block is executed when a try block completes execution whether normally or abruptly. 
In this program, however, the try block does not complete execution at all. 
The System.exit method halts the execution of the current thread and all others dead in their tracks. 
The presence of a finally clause does not give a thread special permission to continue executing.


When System.exit is called, the virtual machine performs two cleanup tasks before shutting down. 
First, it executes all shutdown hooks that have been registered with Runtime.addShutdownHook

This is useful to release resources external to the VM. 
Use shutdown hooks for behavior that must occur before the VM exits. 

In summary, System.exit stops all program threads immediately; 
it does not cause finally blocks to execute, but it does run shutdown hooks before halting the VM. 
Use shutdown hooks to terminate external resources when the VM shuts down. 
It is possible to halt the VM without executing shutdown hooks by calling System.halt, but this method is rarely used.
