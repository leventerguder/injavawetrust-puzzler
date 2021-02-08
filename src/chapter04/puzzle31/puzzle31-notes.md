# Puzzle 31: Ghost of Looper

Provide a declaration for i that turns this loop into an infinite loop:

<pre>
while (i != 0)
    i >>>= 1;
</pre>

Recall that >>>= is the assignment operator corresponding to the unsigned right-shift operator. 
Zeros are shifted in from the left to fill bits vacated by the shift, even if the value being shifted is negative.

For the shift to be legal, i must be of an integral type (byte, char, short, int, or long). 
The unsigned right-shift operator shifts zeros in from the left, so it might seem that the loop could perform only 
as many iterations as the number of bits in the largest integral type, which is 64. This is indeed what would happen 
if you preceded the loop with this declaration:


long i = -1; // -1L has all 64 bits set


How could you possibly turn this into an infinite loop? The key to solving this puzzle is that >>>= is 
a compound assignment operator. 
(The compound assignment operators are *=, /=, %=, +=, -=, <<=, >>=, >>>=, &=, ^=, and |=.)


An unfortunate fact about the compound assignment operators is that they can
silently perform narrowing primitive conversions [JLS 15.26.2],
which are conversions from one numeric type to a less expressive numeric type. 
Narrowing primitive conversions can lose information about the magnitude or precision of numeric values [JLS 5.1.3].


To make this concrete, suppose that you precede the loop with the following declaration:

<pre>
short i = -1;
</pre>

Because the initial value of i ((short)0xffff) is nonzero, the body of the loop is executed.
The first step in the execution of the shift operation is that the value of i is promoted to an int.
All arithmetic operations do this to operands of type short, byte, or char.
This promotion is a widening primitive conversion, so no information is lost.
This promotion performs sign extension, so the resulting int value is 0xffffffff.
This value is then shifted to the right by one bit without sign extension to yield the
int value 0x7fffffff. Finally, this value is stored back into i. In order to store the
int value into the short variable, Java performs the dreaded narrowing primitive conversion,
which simply lops off the high-order 16 bits of the value. This leaves (short)0xffff,
and we are back where we started. The second and successive iterations of the loop behave identically,
so the loop never terminates.


In summary, do not use compound assignment operators on short, byte, or char variables. 
Such expressions perform mixed-type arithmetic, which can be confusing in and of itself. 
Far worse, they perform an implicit narrowing cast, which can discard information. The results can be disastrous.


The lesson for language designers is that languages should not perform narrowing conversions silently. 
One could well argue that Java should have disallowed the use of 
compound assignment operators on short, byte, and char variables.
