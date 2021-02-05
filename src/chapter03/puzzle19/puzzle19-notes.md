# Puzzle 19: Classy Fire

What does the program print?

<pre>
public class Classifier {
    public static void main(String[] args) {
        System.out.println(classify('n') + classify('+') + classify('2'));
    }

    static String classify(char ch) {
        if ("0123456789".indexOf(ch) >= 0)
            return "NUMERAL ";
        if ("abcdefghijklmnopqrstuvwxyz".indexOf(ch) >= 0)
            return "LETTER ";
        /* (Operators not supported yet)
        return "UNKNOWN ";
        if ("+-*/&|!=".indexOf(ch) >= 0)
                return "OPERATOR ";
         */
    }

}

</pre>

If you guessed that this program prints LETTER UNKNOWN NUMBER, you fell for the trap. The program doesn't even compile. 
Let's take another look at the relevant section, this time highlighting the block comment in boldface:

<pre>
       /* (Operators not supported yet)
        return "UNKNOWN ";
        if ("+-*/&|!=".indexOf(ch) >= 0)
                return "OPERATOR ";
         */
</pre>

As you can see, the comment ends inside the string, which quite naturally contains the characters */. 
The resulting program is syntactically invalid. Our attempt to comment out a section of the program failed because 
string literals are not treated specially within comments.

More generally, the text inside of comments is not treated specially in any way [JLS 3.7]. 
Therefore, block comments do not nest. 

<pre>

/*
    /* Add the numbers from to 1 to n */
    int sum = 0;
    for (int i = 1; i <= n; i++)
        sum += i;
*/

</pre>

As you can see, we failed to comment out the original snippet. On the bright side, the resulting code contains 
a syntax error, so the compiler will tell us that we have a problem.

The best way to comment out a section of code is to use a sequence of single-line comments. Most IDEs automate this process:

<pre>
// Code commented out with a sequence of single-line comments
//    /* Add the numbers from 1 to n */
//    int sum = 0;
//    for (int i = 1; i <= n; i++)
//        sum += i;

</pre>



In summary, a block comment does not reliably comment out a section of code. 
Use a sequence of single-line comments instead. 
For language designers, note that nestable block comments are not a good idea. 
They force the compiler to parse the text inside block comments, which causes more problems than it solves.
