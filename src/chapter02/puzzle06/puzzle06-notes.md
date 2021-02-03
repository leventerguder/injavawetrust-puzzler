# Puzzle 6: Multicast

What does it print?

<pre>

public class Multicast {
    public static void main(String[] args) {
        System.out.println((int) (char) (byte) -1);
    }
}

</pre>

It prints 65535, but why?

Java uses two's-complement binary arithmetic, so the int value -1 has all 32 bits set. 
The cast from int to byte is straightforward. It performs a narrowing primitive conversion [JLS 5.1.3], 
which simply lops off all but the low-order 8 bits. 
This leaves a byte value with all 8 bits set, which (still) represents 1.


The cast from byte to char is trickier because byte is a signed type and char unsigned. 
It is usually possible to convert from one integral type to a wider one while preserving numerical value, 
but it is impossible to represent a negative byte value as a char. 
Therefore, the conversion from byte to char is not considered a widening primitive conversion [JLS 5.1.2], 
but a widening and narrowing primitive conversion [JLS 5.1.4]: The byte is converted to an int and the int to a char.


The lesson is simple: If you can't tell what a program does by looking at it, it probably doesn't do what you want. 
Strive for clarity. Although a simple rule describes the sign extension behavior of widening conversions 
involving signed and unsigned integral types, most programmers don't know it. If your program depends on it, 
make your intentions clear.
