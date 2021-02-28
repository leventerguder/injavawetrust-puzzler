# Puzzle 95: Just Desserts

Most of the puzzles in this chapter were quite challenging. This one isn't.
What does each of the following programs print? 
The first two were reported as platform bugs, if you can believe it [Bug 4157460, 4763901]:

<pre>

public class ApplePie {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 100; i++) ;
        {
            count++;
        }
        System.out.println(count);

    }

}


</pre>

<pre>

public class BananaBread {

    public static void main(String[] args) {
        Integer[] array = {3, 1, 4, 1, 5, 9};
        Arrays.sort(array, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i1 < i2 ? -1 : (i2 > i1 ? 1 : 0);
            }
        });
        System.out.println(Arrays.toString(array));
    }

}

</pre>


<pre>
public class ChocolateCake {

    public static void main(String[] args) {

        System.out.println(true ? false : true == true ? false : true);

    }
}

</pre>


If you made it this far, you don't need detailed explanations for these silly puzzles so we'll keep them short and sweet:

A. This program prints 1. It suffers from an excess of punctuation. (Cancer of the semicolon?)
B. This program prints [3, 1, 4, 1, 5, 9] on all implementations that we're aware of.
Technically, its output is undefined. Its comparator suffers from "heads I win, tails you lose"
syndrome.
C. This program prints false. Its typographical layout does not match the precedence of its operators.
A few parentheses might help.
The lesson of this puzzle, and of this entire book, is: Don't code like my brother.
