# Puzzle 52: Sum Fun

This program computes and caches a sum in one class and prints it from another. What does the program print? Here's a hint: 
As you may recall from algebra, the sum of the integers from 1 to n is n(n + 1) / 2.


<pre>
package chapter06.puzzle52;

public class Cache {

    static {
        initializeIfNecessary();
    }

    private static int sum;

    public static int getSum() {
        initializeIfNecessary();
        return sum;
    }

    private static boolean initialized = false;

    private static synchronized void initializeIfNecessary() {
        if (!initialized) {
            for (int i = 0; i < 100; i++)
                sum += i;
            initialized = true;
        }
    }
}

</pre>

<pre>
public class Client {

    public static void main(String[] args) {
        System.out.println(Cache.getSum());
    }

}

</pre>


On cursory inspection, you might think that this program adds the numbers from 1 to 100, but it doesn't. 
Take a closer look at the loop. It is the typical half-open loop, so it goes from 0 to 99. With that in mind, 
you might think that the program prints the sum of the numbers from 0 to 99. Using the formula from the hint, 
this sum is 99 x 100 / 2, or 4,950. The program, however, thinks otherwise. It prints 9900, fully twice this value. 
What accounts for its enthusiasm?

Like the program in Puzzle 49, this program suffers from a class initialization ordering problem.
The Client.main method invokes Cache.getSum. Before the getSum method can be executed, the VM must initialize the class Cache.



Recall that class initialization executes static initializers in the order they appear in the source. 
The Cache class has two static initializers: the static block at the top of the class and the initialization 
of the static field initialized. The block appears first. It invokes the method initializeIfNecessary, 
which tests the field initialized. Because no value has been assigned to this field, it has the default boolean value of false. 
Similarly, sum has the default int value of 0. Therefore, the initializeIfNecessary method does what you'd expect, 
adding 4,950 to sum and setting initialized to true.

After the static block executes, the static initializer for the initialized field sets it back to false, 
completing the class initialization of Cache. Unfortunately, sum now contains the correct cached value,
but initialized contains false: 


The main method in the Client class then invokes Cache.getSum, which in turn invokes initializeIfNecessary. 
Because the initialized flag is false, the initializeIfNecessary method enters its loop, 
which adds another 4,950 to the value of sum, increasing its value to 9,900. The getSum method returns this value, 
and the program prints it.


Unable to decide between eager and lazy initialization, the author tried to do both, resulting in a big mess. 
Use either eager initialization or lazy initialization, never both.


In summary, think about class initialization order, especially when it is nontrivial. 
Do your best to keep the class initialization sequence simple. Use eager initialization unless y
ou have some good reason to use lazy initialization, such as performance or the need to break a cycle in initialization.
