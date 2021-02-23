# Puzzle 77: The Lock Mess Monster

This program runs a little workplace simulation. It starts a worker thread that works or at least pretends to 
work until quitting time. Then the program schedules a timer task representing an evil boss who tries to make sure 
that it's never quitting time. Finally, the main thread, representing a good boss, tells the worker when 
it's quitting time and waits for the worker to finish. What does the program print?

<pre>
import java.util.*;

public class Worker extends Thread {
    private volatile boolean quittingTime = false;

    public void run() {
        while (!quittingTime)
            pretendToWork();
        System.out.println("Beer is good");
    }

    private void pretendToWork() {
        try {
            Thread.sleep(300); // Sleeping on the job?
        } catch (InterruptedException ex) {
        }
    }

    // It's quitting time, wait for worker - Called by good boss
    synchronized void quit() throws InterruptedException {
        quittingTime = true;
        join();
    }

    // Rescind quitting time - Called by evil boss
    synchronized void keepWorking() {
        quittingTime = false;
    }

    public static void main(String[] args) throws InterruptedException {
        final Worker worker = new Worker();
        worker.start();
        Timer t = new Timer(true); // Daemon thread
        t.schedule(new TimerTask() {
            public void run() {
                worker.keepWorking();
            }
        }, 500);
        Thread.sleep(400);
        worker.quit();
    }
}
</pre>


The best way to figure out what this program does is to simulate its execution by hand. 
Here's an approximate time line; the times are relative to the time the program starts running:

300 ms: The worker thread checks the volatile quittingTime field to see whether it's quitting time; 
it isn't, so the thread goes back to "work."

400 ms: The main thread, representing the good boss, invokes the quit method on the worker thread. 
The main thread acquires the lock on the worker Thread instance (because quit is a synchronized method), 
sets quittingTime to TRue, and invokes join on the worker thread. 
The join invocation does not return immediately but waits for the worker thread to complete.

If you tried running the program, though, you found that it prints nothing; it just hangs.

500 ms: The timer task, representing the evil boss, executes. 
It tries to invoke the keepWorking method on the worker thread, but the invocation blocks because 
keepWorking is a synchronized method and the main thread is currently executing a synchronized 
method on the worker thread (the quit method).

600 ms: The worker thread again checks whether it's quitting time. Because the quittingTime field is volatile, 
the worker thread is guaranteed to see the new value of TRue, so it prints Beer is good and completes execution.
This causes the main thread's join invocation to return, and the main thread completes execution. 
The timer thread is a daemon, so it too completes execution, and the program terminates.


There is no guarantee that the events will interleave as indicated in the time line. Neither the Timer class nor the 
Thread.sleep method offers real-time guarantees. That said, it's very likely that these events will interleave 
as indicated by the time line, as the time granularity is so coarse. A hundred milliseconds is an eternity to a computer. 
Moreover, the program hangs repeatably; it looks as if there is something else at work here, and indeed there is.

At 500 ms, when the timer task, representing the evil boss executes, the time line indicates that its 
keepWorking invocation will block because keepWorking is a synchronized method and the main thread is 
currently executing the synchronized quit method on the same object (waiting in Thread.join).
It is true that keepWorking is a synchronized method and that the main thread is currently executing the synchronized quit 
method on the same object. Even so, the timer thread is able to obtain the lock on this object and execute the keepWorking method.


This releases the lock for the duration of the wait. In the case of our program, this allows the timer thread, 
representing the evil boss, to waltz in and set quittingTime back to false, even though the main thread is currently 
executing the synchronized quit method. As a consequence, the worker thread never sees that it's quitting time and 
keeps running forever. The main thread, representing the good boss, never returns from the join method.

The fundamental cause of the misbehavior of the program is that the author of the WorkerThread class used the instance 
lock to ensure mutual exclusion between the quit and keepWorking methods, but this use conflicts with the internal use 
of this lock by the superclass (Thread). The lesson is: Don't assume anything about what a library class will or won't 
do with locks on its instances or on the class, beyond what is guaranteed by the class's specification. 
Any call to a library could result in a call to wait, notify, notifyAll, or a synchronized method. 
All these things can have an effect on application-level code.


Instead, create a separate lock object in a private field. Prior to release 5.0, the correct type to use for 
this lock object was simply Object or a trivial subclass. As of release 5.0, java.util.concurrent.locks provides 
two alternatives: ReentrantLock and ReentrantReadWriteLock. These classes provide more flexibility than 
Object but are a bit more cumbersome to use. 
They cannot be used with a synchronized block, but must be acquired and released explicitly with the aid of a try-finally statement


The most straightforward way to fix the program is to add a private lock field of type 
Object and to synchronize on this object in the quit and keepWorking methods. With these changes, 
the program prints Beer is good as expected.


In summary, never make assumptions about what a library class will or won't do with its locks. 
To isolate yourself from the use of locks by a library class, avoid inheriting from library classes except those 
specifically designed for inheritance [EJ Item 15]. To guarantee that your locks are immune to external interference, 
prevent others from gaining access to your locks by keeping them private.
