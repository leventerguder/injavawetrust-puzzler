# Puzzle 7: Swap Meat

What does it print?

<pre>

public class CleverSwap {
    public static void main(String[] args) {
        int x = 1984;  // (0x7c0)
        int y = 2001;  // (0x7d1)
        x ^= y ^= x ^= y;
        System.out.println("x = " + x + "; y = " + y);
    } 
}

</pre>

As its name implies, this program is supposed to swap the values of the variables x and y. 
It you ran it, you found that it fails miserably, printing x = 0; y = 1984.

The obvious way to swap two variables is to use a temporary variable:
<pre>

int tmp = x;
x = y;
y = tmp;
</pre>


<pre>
// Swaps variables without a temporary - Don't do this!
x = x ^ y;
y = y ^ x;
x = y ^ x;
</pre>

Even back in those days, this technique was seldom justified. 
Now that CPUs have many registers, it is never justified.
Like most "clever" code, it is far less clear than its naive counterpart and far slower. 
Still, some programmers persist in using it. 
Worse, they complicate matters by using the idiom illustrated in this puzzle, 
which combines the three exclusive OR operations into a single statement.

<pre>
// Rube Goldberg would approve, but don't ever do this!
y = (x ^= (y ^= x)) ^ y;
</pre>

The lesson is simple: Do not assign to the same variable more than once in a single expression. 
Expressions containing multiple assignments to the same variable are confusing and seldom do what you want. 
Even expressions that assign to multiple variables are suspect. More generally, avoid clever programming tricks. 
They are bug-prone, difficult to maintain, and often run more slowly than the straightforward code they replace

Language designers might consider prohibiting multiple assignments to the same variable in one expression, 
but it would not be feasible to enforce this prohibition in the general case, because of aliasing. 
For example, consider the expression x = a[i]++ - a[j]++. 
Does it increment the same variable twice? 
That depends on the values of i and j at the time the expression is evaluated,
and there is no way for the compiler to determine this in general.

Extra :
https://www.geeksforgeeks.org/swap-two-numbers-without-using-temporary-variable/
