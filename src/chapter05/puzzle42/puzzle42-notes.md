# Puzzle 42: Thrown for a Loop

The following program loops through a sequence of int arrays and keeps track of 
how many of the arrays satisfy a certain property. What does the program print?

<pre>
public class Loop {
    public static void main(String[] args) {
        int[][] tests = {{6, 5, 4, 3, 2, 1}, {1, 2},
                {1, 2, 3}, {1, 2, 3, 4}, {1}};
        int n = 0;

        try {
            int i = 0;
            while (true) {
                if (thirdElementIsThree(tests[i++]))
                    n++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // No more tests to process
        }
        System.out.println(n);
    }

    private static boolean thirdElementIsThree(int[] a) {
        return a.length >= 3 & a[2] == 3;
    }
}
</pre>

The thirdElementIsThree method returns true if its argument has three or more elements 
and the third element is equal to 3. 
This is true for two of the five int arrays in tests, 
so it looks as though the program should print 2. If you ran it, you found that it prints 0.
Surely there must be some mistake?


In fact, there are two mistakes. The first mistake is that the program uses the hideous loop idiom that 
depends on an array access throwing an exception. This idiom is not only unreadable but also extremely slow.
Do not use exceptions for loop control; use exceptions only for exceptional conditions [EJ Item 39]. T
o correct this mistake, replace the entire try-finally block with the standard idiom for looping over an array:

<pre>

for (int i = 0; i < tests.length; i++)
    if (thirdElementIsThree(tests[i]))
        successCount++;
</pre>

you can use the for-each construct instead:

<pre>
for (int[] test : tests)
    if (thirdElementIsThree(test))
        successCount++;
</pre>


Clearly, there is a bug in the thirdElementIsThree method: It is throwing an ArrayIndexOutOfBoundsException. 
This exception was previously masquerading as the end of the hideous exception-based loop.

It turns out that the & operator has another meaning. In addition to its common use as the bitwise AND operator 
for integral operands, it is overloaded to function as the logical AND operator when applied 
to boolean operands [JLS 15.22.2]. This operator differs from the more commonly used conditional 
AND operator (&&) in that the & operator always evaluates both of its operands, whereas the && operator 
does not evaluate its right operand if its left operand evaluates to false [JLS 15.23].

Fixing this method is as simple as replacing the & operator with the && operator. 
With this change, the program prints 2 as expected:

<pre>
private static boolean thirdElementIsThree(int[] a) {
    return a.length >= 3 && a[2] == 3;
}
</pre>

In summary, do not use the hideous loop idiom where an exception is used in preference 
to an explicit termination test; this idiom is unclear, slow, and masks other bugs.
Be aware of the existence of the logical AND and OR operators, and do not fall prey to unintentional use. 
For language designers, this is another example where operator overloading is confusing. 
It is not clear that there is a case for providing the logical AND and OR operators in addition to 
their conditional counterparts. If these operators are to be supported, 
they should be visually distinct from their conditional counterparts.
