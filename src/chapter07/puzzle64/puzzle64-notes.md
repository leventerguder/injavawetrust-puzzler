# Puzzle 64: The Mod Squad

This program generates a histogram of the numbers mod 3. What does it print?

<pre>
public class Mod {

    public static void main(String[] args) {

        final int MODULUS = 3;
        int[] histogram = new int[MODULUS];

        System.out.println(Math.abs(-1));
        System.out.println(Math.abs(Integer.MIN_VALUE));

        int i = Integer.MIN_VALUE;
        do {
            histogram[Math.abs(i) % MODULUS]++;
        } while (i++ != Integer.MAX_VALUE);

        for (int j = 0; j < MODULUS; j++)
            System.out.println(histogram[j] + " ");


    }
}

</pre>



The program initializes the int array histogram with one location for each of the mod 3 values (0, 1, and 2). 
All three locations are initially 0. Then the program loops the variable i over all 2^32 int values, 
using the idiom introduced in Puzzle 26. Because the integer remainder operator (%) can return a 
negative value if its first operand is negative, as discussed in Puzzle 1, the program takes 
the absolute value of i before computing its remainder when divided by 3. 
Then it increments the array location indexed by this remainder. After the loop finishes, 
the program prints the contents of the histogram array, whose elements represent the number 
of int values whose mod 3 values are 0, 1, and 2.

Exception in thread "main" ArrayIndexOutOfBoundsException: -2

The problem lies in the program's use of the Math.abs method, which results in erroneous mod 3 values.

To answer that question, we must go to the documentation for Math.abs. 
This method is named a bit deceptively. It nearly always returns the absolute value of its argument, 
but in one case, it does not. The documentation says, "If the argument is equal to the value of Integer.MIN_VALUE, 
the result is that same value, which is negative.

To fix the program, we must replace the bogus mod calculation (Math.abs(i) % MODULUS) with one that actually works. 
If we replace this expression with an invocation of the following method, the program produces the expected output 
of 1431655765 1431655766 1431655765:


<pre>
    private static int mod(int i, int modulus) {
        int result = i % modulus;
        return result < 0 ? result + modulus : result;
    }

</pre>


The lesson of this puzzle is that Math.abs is not guaranteed to return a nonnegative result. If its argument is 
Integer.MIN_VALUE or Long.MIN_VALUE for the long version of the method it returns its argument

Briefly, there is no int value that represents the negation of Integer.MIN_VALUE and no long value that represents 
the negation of Long.MIN_VALUE. For library designers, it might have been preferable if Math.abs threw 
IllegalArgumentException when it was passed Integer.MIN_VALUE or Long.MIN_VALUE.
