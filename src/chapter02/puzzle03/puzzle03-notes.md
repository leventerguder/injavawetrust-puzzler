# Puzzle 3: Long Division

This puzzle is called Long Division because it concerns a program that divides two long values. 
The dividend represents the number of microseconds in a day; the divisor, the number of milliseconds in a day. 
What does the program print?

<pre>
public class LongDivision {
    public static void main(String[] args) {
        final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
    } 
}
</pre>

The problem is that the computation of the constant MICROS_PER_DAY does overflow.
Although the result of the computation fits in a long with room to spare, it doesn't fit in an int.
The computation is performed entirely in int arithmetic, 
and only after the computation completes is the result promoted to a long. 


So why is the computation performed in int arithmetic? 
Because all the factors that are multiplied together are int values. When you multiply two int values, 

It's easy to fix the program by using a long literal in place of an int as the first factor in each product. 
This forces all subsequent computations in the expression to be done with long arithmetic.

The lesson is simple: When working with large numbers, watch out for overflow
because a variable is large enough to hold a result doesn't mean that the computation leading to 
the result is of the correct type. When in doubt, perform the entire computation using long arithmetic.

The lesson for language designers is that it may be worth reducing the likelihood of silent overflow. 
This could be done by providing support for arithmetic that does not overflow silently. 
Programs could throw an exception instead of overflowing, as does Ada, or they could switch to 
a larger internal representation automatically as required to avoid overflow, as does Lisp. 
Both of these approaches may have performance penalties associated with them. 
Another way to reduce the likelihood of silent overflow is to support target typing, 
but this adds significant complexity to the type system
