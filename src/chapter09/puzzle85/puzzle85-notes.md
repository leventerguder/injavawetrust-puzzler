# Puzzle 85: Lazy Initialization

This poor little class is too lazy to initialize itself in the usual way, so it calls on the help of background thread. 
What does the program print? Is it guaranteed to print the same thing every time you run it?

<pre>
public class Lazy {

    private static boolean initialized = false;

    static {
        Thread t = new Thread(new Runnable() {
            public void run() {
                initialized = true;
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(initialized);
    }

}
</pre>

This program looks straightforward, if a bit strange. The static field initialized is initially set to false. 
Then the main thread creates a background thread whose run method sets initialized to true. 
The main thread starts the background thread and waits for it to complete by calling join. 
Once the background thread has completed, there can be no doubt that initialized has been set to true. 
Then and only then does the main thread invoke main, which prints the value of initialized. 
Surely the program must print true ?
If only it were so. If you ran the program, you found that it prints nothing; it just hangs.

In order to understand the behavior of this program, we have to simulate its initialization in detail. 
When a thread is about to access a member of a class, the thread checks to see if the class has been initialized. 
Ignoring serious errors, there are four possible cases [JLS 12.4.2]:


1. The class is not yet initialized.
2. The class is being initialized by the current thread: a recursive request for initialization. 
3. The class is being initialized by some thread other than the current thread.
4. The class is already initialized.


When the main thread invokes Lazy.main, it checks whether the class Lazy has been initialized. 
It hasn't (case 1), so the thread records that initialization is now in progress and begins to initialize the class. 
As per our previous analysis, the main thread now sets initialized to false, creates and starts a background 
thread whose run method sets initialized to TRue, and waits for the background thread to complete.
Then the fun begins.

The background thread invokes its run method. Before the thread sets Lazy.initialized to true, it too checks whether 
the class Lazy has been initialized. This time, the class is currently being initialized by another thread (case 3).
Under these circumstances, the current thread, which is the background thread, waits on the Class object until 
initialization is complete. Unfortunately, the thread that is doing the initialization, the main thread, is waiting 
for the background thread to complete. Because the two threads are now waiting for each other, the program is deadlocked. 
That's all there is to it, and what a pity it is.

There are two ways to fix the problem. By far the best way is not to start any background threads during 
class initialization: Sometimes, two threads aren't better than one. More generally, keep class initialization 
as simple as possible. A second way to fix the problem is to allow the main thread to finish initializing 
the class before waiting for the background thread:

<pre>
// Bad way to eliminate the deadlock. Complex and error prone.
public class LazyFixed {

    private static boolean initialized = false;
    private static Thread t = new Thread(new Runnable() {
        public void run() {
            initialized = true;
        }
    });

    static {
        t.start();
    }

    public static void main(String[] args) {

        try {
            t.join();
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }

        System.out.println(initialized);
    }

}

</pre>

Although this does eliminate the deadlock, it is a very bad idea. The main thread waits for the background thread 
to finish its work, but other threads don't have to. 
They can use the class Lazy as soon as the main thread has finished initializing it, 
allowing them to observe initialized when its value is still false.


In summary, waiting for a background thread during class initialization is likely to result in deadlock. 
Keep class initialization sequences as simple as possible. Automatic class initialization is known to be a 
very difficult language design problem, and Java's designers did a fine job in this area. Still, there are many ways 
to shoot yourself in the foot if you write complex class initialization code.
