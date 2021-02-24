# Puzzle 84: Rudely Interrupted

In this program, a thread tries to interrupt itself and then checks whether it succeeded. What does the program print?

<pre>
public class SelfInterruption {

    public static void main(String[] args) {
        
        Thread.currentThread().interrupt();

        if (Thread.interrupted()) {
            System.out.println("Interrupted: " + Thread.interrupted());
        } else {
            System.out.println("Not interrupted: " + Thread.interrupted());
        }
    }
}
</pre>


Although it is not common for a thread to interrupt itself, it isn't unheard of, either. 
When a method catches an InterruptedException and is not prepared to deal with it, the method usually 
rethrows the exception. Because it is a checked exception, a method can rethrow it only if the method declaration permits. 
If not, the method can "reraise" the exception without rethrowing it, by interrupting the current thread. 
This works fine, so our program should have no trouble interrupting itself. 
Therefore, the program should take the first branch of the if statement and print Interrupted: true. 
If you ran the program, you found that it doesn't. 
It doesn't print Not interrupted: false, either; it prints Interrupted: false.

Calling Thread.interrupted always clears the interrupted status of the current thread.

The lesson of this puzzle is: Don't use Thread.interrupted unless you want to clear the interrupted 
status of the current thread. If you just want to query it, use isInterrupted instead. 
The lesson for API designers is that methods should have names that describe their primary functions. 
Given the behavior of Thread.interrupted, it should have been named clearInterruptStatus. I
ts return value is secondary to the state change it effects. 

Especially when a method has a name that is less than perfect, 
it is important that its documentation clearly describe its behavior.
