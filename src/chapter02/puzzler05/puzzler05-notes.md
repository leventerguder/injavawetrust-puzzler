# Puzzle 5: The Joy of Hex

The following program adds two hexadecimal, or "hex," literals and prints the result in hex. What does the program print?

<pre>

public class JoyOfHex {
    public static void main(String[] args) {
        System.out.println(
            Long.toHexString(0x100000000L + 0xcafebabe));
} }
</pre>

It seems obvious that the program should print 1cafebabe. 
After all, that is the sum of the hex numbers 100000000(16) and cafebabe(16). 
The program uses long arithmetic, which permits 16 hex digits, so arithmetic overflow is not an issue. 
Yet, if you ran the program, you found that it printscafebabe, with no leading 1 digit

Decimal literals have a nice property that is not shared by hexadecimal or octal literals: 
Decimal literals are all positive [JLS 3.10s.1].
To write a negative decimal constant, you use the unary negation operator (-) in combination with a decimal literal. 

Not so for hexadecimal and octal literals. They can take on both positive and negative values. 
Hex and octal literals are negative if their high-order bit is set.
In this program, the number 0xcafebabe is an int constant with its high-order bit set, so it is negative.
It is equivalent to the decimal value -889275714.

The addition performed by the program is a mixed-type computation: The left operand is of type long, 
and the right operand is of type int. To perform the computation, 
Java promotes the int value to a long with a widening primitive conversion [JLS 5.1.2] and adds the two long values.
Because int is a signed integral type, the conversion performs sign extension: 
It promotes the negative int value to a numerically equal long value.

The right operand of the addition, 0xcafebabe, is promoted to the long value 0xffffffffcafebabeL.
This value is then added to the left operand, which is 0x100000000L

0xffffffffcafebabeL 
0x0000000100000000L
+ -------------------
0x00000000cafebabeL



Fixing the problem is as simple as using a long hex literal to represent the right operand. 
This avoids the damaging sign extension, and the program prints the expected result of 1cafebabe:

<pre>
public class JoyOfHex {
    public static void main(String[] args) {
        System.out.println(
            Long.toHexString(0x100000000L + 0xcafebabeL));
    } 
}

</pre>


The lesson of this puzzle is that mixed-type computations can be confusing, 
more so given that hex and octal literals can take on negative values without an explicit minus sign. 
To avoid this sort of difficulty, it is generally best to avoid mixed-type computations.

For language designers, it is worth considering support for unsigned integral types, 
which eliminate the possibility of sign extension
