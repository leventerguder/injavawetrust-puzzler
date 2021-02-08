# Puzzle 33: Looper Meets the Wolfman

Provide a declaration for i that turns this loop into an infinite loop. 

<pre>
while (i != 0 && i == -i) {
}

</pre>

Yet another puzzling looper. In the boolean expression (i != 0 && i == -i), the unary minus operator is applied to i, 
which implies that its type must be numeric: It is illegal to apply the unary minus operator to a non-numeric operand.

Therefore, we are looking for a nonzero numeric value that is equal to its own negation.
NaN does not satisfy this property, as it is not equal to any value, so i must represent an actual number. 
Surely there is no number with this property?


The signed integral types use two's-complement arithmetic: To negate a value, you flip every bit and add 1 to the 
result [JLS 15.15.4]. One big advantage of two's-complement arithmetic is that there is a unique representation for 0.

One big advantage of two's-complement arithmetic is that there is a unique representation for 0. If you negate the int
value 0, you get 0xffffffff + 1, which is 0.


In fact, there is exactly one such int value, and it is Integer.MIN_VALUE, or -2^31. Its hexadecimal 
representation is 0x8000000. The sign bit is 1, and all the other bits are 0. If we negate this value, 
we get 0x7fffffff + 1, which is 0x8000000, or Integer.MIN_VALUE! 


In summary, Java uses two's-complement arithmetic, which is asymmetric. 
The signed integral types (int, long, byte, and short) each have one more negative value than positive, 
which is always the minimum value representable in the type. Negating Integer.MIN_VALUE doesn't change its value, 
and the same holds true for Long.MIN_VALUE. Negating Short.MIN_VALUE and casting the resulting int value back to a 
short returns the original value (Short.MIN_VALUE). A similar result holds for Byte.MIN_VALUE. More generally, 
watch out for overflow: Like the Wolfman, it's a killer.ss
