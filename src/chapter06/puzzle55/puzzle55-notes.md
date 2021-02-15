# Puzzle 55: Creationism

What does the program print?

<pre>
public class Creator {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++)
            Creature creature = new Creature();
         System.out.println(Creature.numCreated());
    }

}

</pre>

<pre>
public class Creature {

    private static long numCreated = 0;

    public Creature() {
        numCreated++;
    }

    public static long numCreated() {
        return numCreated;
    }
}
</pre>


This is a trick question. The program looks as though it ought to print 100,
but it doesn't print anything, because it doesn't compile

A local variable declaration looks like a statement but technically speaking is not; it is a 
local variable declaration statement [JLS 14.4]. 
The syntax of the language does not allow a local variable declaration statement as the statement repeated by a for, 
while, or do loop [JLS 14.12-14]. A local variable declaration can appear only as a statement directly within a block. 
(A block is a pair of curly braces and the statements and declarations contained within it.)

There are two ways to fix the problem. The obvious way is to place the declaration in a block:

<pre>
for (int i = 0; i < 100; i++) {
    Creature creature = new Creature();
}
</pre>


Also note that the creation counting strategy in this puzzle is not thread-safe.

<pre>
public class CreatureThreadSafe {

    private static long numCreated;

    public CreatureThreadSafe() {
        synchronized (Creature.class) {
            numCreated++;
        }
    }

    public static synchronized long numCreated() {
        return numCreated;
    }
}

</pre>


you can use an AtomicLong instance, which obviates the need for synchronization in the face of concurrency.

<pre>
// Thread-safe creation counter using AtomicLong;
public class CreatureAtomic {

    private static AtomicLong numCreated = new AtomicLong();

    public CreatureAtomic() {
        numCreated.incrementAndGet();
    }

    public static long numCreated() {
        return numCreated.get();
    }
}

</pre>

Note that it is not sufficient to declare numCreated to be volatile. 
The volatile modifier guarantees that other threads will see the most recent value assigned to a field, 
but it does not make the increment operation atomic.


In summary, a local variable declaration cannot be used as the repeated statement in a for, while, or 
do loop but can appear only as a statement directly within a block. Also, when using a variable to 
count instance creations, use a long rather than an int, to prevent overflow. Finally, 
if you are going to create instances in multiple threads, either synchronize access to 
the instance counter or use an AtomicLong.
