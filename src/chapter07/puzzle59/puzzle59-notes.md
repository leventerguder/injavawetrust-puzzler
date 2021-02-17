# Puzzle 59: What's the Difference?

What does the program print?

<pre>
import java.util.*;

public class Dıfferences {

    public static void main(String[] args) {

        int vals[] = {789, 678, 567, 456,
                345, 234, 123, 012};

        Set<Integer> diffs = new HashSet<Integer>();

        for (int i = 0; i < vals.length; i++)
            for (int j = i; j < vals.length; j++)
                diffs.add(vals[i] - vals[j]);
            
        System.out.println(diffs.size());
    }
}

</pre>



The outer loop iterates over every element in the array. The inner loop iterates over every element from the current
element in the outer-loop iteration to the last element in the array. Therefore, the nested loop iterates over every
possible pair of elements from the array exactly once.


if you print the contents of the array, here is what you will see:

<pre>
[789, 678, 567, 456, 345, 234, 123, 10]
</pre>

Why is the final element of the array 10 instead of 12? Because integer literals beginning with a
0 are interpreted as octal values [JLS 3.10.1].


The lesson of this puzzle is simple: Never pad an integer literal with zeros; this turns it into an octal literal.
The intentional use of octal integer literals is so rare that you should probably comment every use. 
