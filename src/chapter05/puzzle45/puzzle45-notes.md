# Puzzle 45: Exhausting Workout

This puzzle tests your knowledge of recursion. What does this program do?

<pre>
public class Workout {


    public static void main(String[] args) {
        workHard();
        System.out.println("It's nap time.");
    }

    private static void workHard() {

        try {
            workHard();
        } finally {
            workHard();
        }
    }
}
</pre>



If it weren't for the try-finally statement, it would be obvious what this program does: 
The workHard method calls itself recursively until the program throws a StackOverflowError, at which point it terminates
with an uncaught exception. The try-finally statement complicates matters. When it tries to throw a StackOverflowError, 
the program ends up in a finally block in the workHard method, where it calls itself recursively. 

This seems like a prescription for an infinite loop. Does the program loop indefinitely? If you run it, 
it appears to do exactly that, but the only way to know for sure is to analyze its behavior.



The Java virtual machine limits the stack depth to some preset level. When this level is exceeded, 
the VM throws a StackOverflowError. In order to make it easier to think about the behavior of the program, 
let's pretend that the stack depth limit is much smaller than it really is: say, 3. Now let's trace the execution.



The main method calls workHard, which calls itself recursively from its try block. 
Again it calls itself from its try block. At this point, the stack depth is 3.
When the workHard method attempts once more to call itself from its TRy block, the call fails immediately with 
a StackOverflowError. This error is caught in the innermost finally block, where the stack depth is already 3. 
From there the workHard method attempts to call itself recursively, but the call fails with a StackOverflowError. 
This error is caught in the finally block one level up, where the stack depth is 2. 
The call from this finally block has the same behavior as the call from the corresponding try block: 
It results eventually in a StackOverflowError. A pattern appears to be emerging, and indeed it is.


A quick experiment shows that many VMs limit stack depth to 1,024. 
The number of calls is therefore 1 + 2 + 4 + 8 ... + 2^1,024 = 2^1,025 - 1. 
The number of exceptions thrown is 2^1,024. Let's assume that our machine can execute 1010 calls 
per second and generate 10^10 exceptions per second, which is quite generous by current standards.
Under these assumptions, the program will terminate in about 1.7 x 10291 years. 
To put this in perspective, the lifetime of our sun is estimated at 10$^10 years, 
so it is a safe bet that none of us will be around to see this program terminate.
Although it isn't an infinite loop, it might as well be.


Technically speaking, the call diagram is a complete binary tree whose depth is the stack depth limit of the VM. 
The execution of the Workout program amounts to a preorder traversal of this tree. In a preorder traversal, 
the program visits a node and then recursively visits its left and right subtrees. 
One call is made for each edge in the tree, and one exception is thrown for each leaf node.
