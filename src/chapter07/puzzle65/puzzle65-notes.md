# Puzzle 65: A Strange Saga of a Suspicious Sort


This program sorts an array of randomly chosen Integer instances, using a custom comparator, 
and then prints a word describing the order of the array. Recall that the Comparator interface has a single method, 
compare, which returns a negative value if its first argument is less than its second, zero if its two 
arguments are equal, and a positive value if its first argument is greater than its second. 
This program is a showcase for release 5.0 features. It uses autoboxing and unboxing, generics, and enum types. 
What does it print?

<pre>
import java.util.*;

public class SuspiciousSort {

    public static void main(String[] args) {

        Random rnd = new Random();
        Integer[] arr = new Integer[100];


        for (int i = 0; i < arr.length; i++)
            arr[i] = rnd.nextInt();

        Comparator<Integer> cmp = new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        };

        Arrays.sort(arr, cmp);
        System.out.println(order(arr));

    }

    enum Order {ASCENDING, DESCENDING, CONSTANT, UNORDERED}

    ;

    static Order order(Integer[] a) {
        boolean ascending = false;
        boolean descending = false;

        for (int i = 1; i < a.length; i++) {
            ascending |= (a[i] > a[i - 1]);
            descending |= (a[i] < a[i - 1]);
        }

        if (ascending && !descending)
            return Order.ASCENDING;
        if (descending && !ascending)
            return Order.DESCENDING;
        if (!ascending)
            return Order.CONSTANT;  // All elements equal
        return Order.UNORDERED;     // Array is not sorted
        
    }

}

</pre>

The main method creates an array of Integer instances, initializes it with random values, 
and sorts the array using the comparator cmp. This comparator's compare method returns its second argument minus its first, 
which is positive if its second argument represents a larger value than its first, zero if they're equal,
and negative if its second argument represents a smaller value than its first. 
This is the opposite of what is normally done by the compare method, so this comparator should impose a descending order.

After sorting the array, the main method passes it to the static method order and prints the result returned by this method. 

If you ran it, you almost certainly saw it print UNORDERED. Why would it do such a thing?

The order method is straightforward, and it does not lie. The Arrays.sort method has been around for years, and it works fine. 
This leaves only one place tolook for bugs: the comparator. At first glance, it may seem unlikely that the comparator is broken.

When you subtract two int or long values, the result can overflow, in which case it will have the wrong sign.

<pre>
public class Overflow {

    public static void main(String[] args) {
        int x = -2000000000;
        int z = 2000000000;
        System.out.println(x - z);
    }

}
</pre>

}
Clearly, x is less than z, yet the program prints 294967296, which is positive.
Given that this comparison idiom is broken, why is it used so commonly? Because it works most of the time.
It breaks only if the numbers to which it is applied differ by more than Integer.MAX_VALUE.
This means that for many applications, failures won't be observed in practice.
Worse, they may be observed infrequently enough that the bug will never get found and fixed.


We can fix our program by substituting a Comparator implementation that obeys the general contract. 
Because we want to reverse the natural order, we don't even have to write our own comparator. 
The Collections class provides one that's made to order. If you replace the original Arrays.sort invocation 
by Arrays.sort(arr, Collections.reverseOrder()), the program will print DESCENDING as expected.

Alternatively, you can write your own comparator. 
The following code is not "clever," but it works, causing the program to print DESCENDING as expected

<pre>
public int compare(Integer i1, Integer i2) {
    return (i2 < i1 ? -1 : (i2 == i1 ? 0 : 1));
}

</pre>


This puzzle has several lessons. 
The most specific is: Do not use a subtraction-based comparator unless you are sure that 
the difference between values will never be greater than Integer.MAX_VALUE [EJ Item 11].

More generally, beware of int overflow, as discussed in Puzzles 3, 26, and 33. Another lesson is that you should 
avoid "clever" code. Strive to write clear, correct code, and do not optimize it unless it proves necessary [EJ Item 37].
