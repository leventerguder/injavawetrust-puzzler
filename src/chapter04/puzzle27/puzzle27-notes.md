# Puzzle 27: Shifty i's

When you read it, remember that Java uses two's-complement binary arithmetic, 
so the representation of -1 in any signed integral type (byte, short, int, or long) has all its bits set:

<pre>
public class Shifty {

    public static void main(String[] args) {

        int i = 0;
        while (-1 << i != 0)
            i++;
        System.out.println(i);
    }
}

</pre>

The constant -1 is the int value with all 32 bits set (0xffffffff). 
The left-shift operator shifts zeroes in from the right to fill the low-order bits vacated by the shift, 
so the expression (-1 << i) has its rightmost i bits set to 0 and the remaining 32 - i bits set to 1. 
Clearly, the loop completes 32 iterations, as -1 << i is unequal to 0 for any i less than 32. 
You might expect the termination test to returnfalsewheniis 32, causing the program to print 32, 
but it doesn't print 32. In fact, it doesn't print anything but goes into an infinite loop.


The problem is that (-1 << 32) is equal to -1 rather than 0, 
because shift operators use only the five low-order bits of their right operand as the shift distance, 
or six bits if the left operand is a long [JLS 15.19]. This applies to all three shift operators: <<, >>, and >>>.
The shift distance is always between 0 and 31, or 0 and 63 if the left operand is a long. It is calculated mod 32, 
or mod 64 if the left operand is a long. Attempting to shift an int value 32 bits or a long 
value 64 bits just returns the value itself. here is no shift distance that discards all 32 bits of an int value or all 64 bits of a long value.


There is another surprising consequence of the aforementioned behavior of shift operators. 
Many programmers expect a right-shift operator with a negative shift distance to function as a left shift and vice-versa.
This is not the case. A right shift always functions as a right shift, and a left shift always functions as a left shift.
Negative shift distances are made positive by lopping off all but the five low-order bits six bits if the left operand i
s a long. So, for example, shifting an int to the left with a shift distance of -1 has the effect of shifting it 31 bits to the left.

In summary, shift distances are calculated mod 32 or, if the left operand is a long, mod 64. It is therefore impossible 
to shift away an entire value by using any shift operator or distance. 
Also, it is impossible to perform a left shift with a right-shift operator or vice-versa. Use a constant shift distance 
if possible, and exercise care if the shift distance can't be made constant.

Language designers should perhaps consider restricting shift distances to the range from 0 to the type size in bits 
and changing the semantics of shifting a value by the type size to return 0. Although this would avoid the confusion 
illustrated by this puzzle, it could have negative performance consequences; Java's semantics for the shift operators 
are those of the shift instructions on many processors.
