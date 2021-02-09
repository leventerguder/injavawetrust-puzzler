# Puzzle 35: Minute by Minute

What does it print?

<pre>

public class Clock {

    public static void main(String[] args) {

        int minutes = 0;
        for (int ms = 0; ms < 60 * 60 * 1000; ms++)
            if (ms % 60 * 1000 == 0)
                minutes++;
        System.out.println(minutes);

    }
}

</pre>


It prints 60000. Why does it increment minutes so often?

The problem lies in the boolean expression(ms % 60*1000 == 0). 
You might think that this expression is equivalent to (ms % 60000 == 0), but it isn't. 
The remainder and multiplication operators have the same precedence [JLS 15.17], 
so the expression ms % 60*1000 is equivalent to (ms % 60) * 1000. This expression is equal to 0 if (ms % 60) is 0, 
so the loop increments minutes every 60 iterations. This accounts for the final result being off by a factor of a thousand.

The easiest way to fix the program is to insert a pair of parentheses into the boolean expression 
to force the correct order of evaluation:

<pre>

if (ms % (60 * 1000) == 0)
    minutes++;
</pre>

The expression ms % 60*1000 in the original program was laid out to fool you into thinking that multiplication
has higher precedence than remainder. The compiler, however, ignores this white space, so never 
use spacing to express grouping; use parentheses. Spacing can be deceptive, but parentheses never lie.
