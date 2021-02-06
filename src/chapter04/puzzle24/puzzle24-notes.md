# Puzzle 24: A Big Delight in Every Byte

What does the program print?

<pre>
public class BigDelight {

    public static void main(String[] args) {

        for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
            if (b == 0x90)
                System.out.print("Joy!");
        }
    }

}

</pre>

Simply put, 0x90 is an int constant that is outside the range of byte values. 
This is counterintuitive because Ox90 is a two-digit hexadecimal literal. 

Legal byte values range from -128 to +127, but the int constant 0x90 is equal to +144.

The comparison of a byte to an int is a mixed-type comparison. 
If you think of byte values as apples and int values as oranges, the program is comparing apples to oranges.
Consider the expression ((byte)0x90 == 0x90). Appearances notwithstanding, 
it evaluates to false. To compare the byte value (byte)0x90 to the int value 0x90, 
Java promotes the byte to an int with a widening primitive conversion [JLS 5.1.2] 
and compares the two int values. Because byte is a signed type, 
the conversion performs sign extension, promoting negative byte values to 
numerically equal int values. In this case, the conversion promotes (byte)0x90 to the int value -112, 
which is unequal to the int value 0x90, or +144.


Mixed-type comparisons are always confusing because the system is forced to promote one operand to match the 
type of the other. The conversion is invisible and may not yield the results that you expect.

<pre>
private static final byte TARGET = 0x90; // Broken!
</pre>

Unfortunately, it doesn't compile. The constant declaration is broken, and the compiler will 
tell you the problem: 0x90 is not a valid value for the type byte. 
If you fix the declaration as follows, the program will work fine:

<pre>
private static final byte TARGET = (byte)0x90;
</pre>


To summarize: Avoid mixed-type comparisons, because they are inherently confusing (Puzzle 5). 
To help achieve this goal, use declared constants in place of "magic numbers." 
You already knew that this was a good idea; it documents the meanings of constants, 
centralizes their definitions, and eliminates duplicate definitions. 
Now you know that it also forces you to give each constant a type appropriate for its use,
eliminating one source of mixed-type comparisons.


The lesson for language designers is that sign extension of byte values is a common source of bugs and confusion. 
The masking that is required in order to suppress sign extension clutters programs, making them
less readable. Therefore, the byte type should be unsigned. Also, consider providing literals for all primitive types, 
reducing the need for error-prone type conversions (Puzzle 27).
