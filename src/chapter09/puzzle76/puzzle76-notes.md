# Puzzle 76: Ping Pong

This program consists entirely of synchronized static methods. What does it print? 
Is it guaranteed to print the same thing every time you run it?

<pre>
public class PingPong {

    public static synchronized void main(String[] a) {
        Thread t = new Thread() {
            public void run() {
                pong();
            }
        };
        t.run();
        System.out.print("Ping");
    }

    static synchronized void pong() {
        System.out.print("Pong");
    }
}

</pre>

In a multithreaded program, it is generally a good bet that the behavior can vary from run to run, 
but this program always prints the same thing. Before a synchronized static method executes, 
it obtains the monitor lock associated with its Class object [JLS 8.4.3.6]. Therefore, the main thread acquires 
the lock on PingPong.class before creating the second thread. As long as the main thread holds on to this lock, 
the second thread can't execute a synchronized static method. In particular, the second thread can't execute 
the pong method until the main method prints Ping and completes execution. Only then does the main thread 
relinquish the lock, allowing the second thread to acquire it and print Pong. 
This analysis leaves little doubt that the program should always print PingPong. 
There is one small problem: If you tried the program, you found that it always prints PongPing. What on earth is going on?

Strange as it may seem, this is not a multithreaded program. Not a multithreaded program? 
How can that be? Surely it creates a second thread. Well, yes, it does create a second thread, 
but it never starts that thread.
Instead, the main thread simply invokes the run method of the new Thread instance, 
and the run method executes synchronously in the main thread.

Fixing the program is as simple as changing t.run to t.start. 
Once this is done, the program reliably prints PingPong as expected.


The lesson is simple: Be careful not to invoke a thread's run method when you mean to invoke its start method.

The Thread class has a public run method because it implements Runnable, but it didn't have to be that way. 
An alternative design would be for each Thread instance to encapsulate a Runnable,
giving rise to composition in place of interface inheritance.
