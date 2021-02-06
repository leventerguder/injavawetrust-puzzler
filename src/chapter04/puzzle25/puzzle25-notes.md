# Puzzle 25: Inclement Increment

This program increments a variable repeatedly and then prints its value. What is it?

<pre>
public class Increment {

    public static void main(String[] args) {

        int j = 0;
        for (int i = 0; i < 100; i++)
            j = j++;
        System.out.println(j);

    }
}
</pre>

At first glance, the program might appear to print 100. After all, it does increment j 100 times. 
Perhaps surprisingly, it does not print 100 but 0. All that incrementing gets us nowhere. Why?

As the puzzle's title suggests, the problem lies in the statement that does the increment:

<pre>
j = j++;
</pre>

Presumably, the author of the statement meant for it to add 1 to the value of j, which is what the expression j++ does.
Unfortunately, the author inadvertently assigned the value of this expression back to j. 
When placed after a variable, the ++ operator functions as the postfix increment operator [JLS 15.14.2]: 
The value of the expression j++ is the original value of j before it was incremented. 
Therefore, the preceding assignment first saves the value of j, then sets j to its value plus 1, and, finally, 
resets j back to its original value. In other words, the assignment is equivalent to this sequence of statements:

<pre>
int tmp = j;
j = j + 1;
j = tmp;
</pre>

The program repeats this process 100 times, after which the value of j is exactly what it was before the loop, or 0.

Fixing the program is as simple as removing the extraneous assignment from the loop, leaving:

<pre>
for (int i = 0; i < 100; i++)
    j++;

</pre>

With this modification, the program prints 100 as expected.

The lesson is this the same as in Puzzle 7: Do not assign to the same variable more than once in a single expression. 
An expression containing multiple assignments to the same variable is confusing and seldom does what you want.
