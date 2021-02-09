# Puzzle 34: Down for the Count

What does the program print?

<pre>
public class Count {

    public static void main(String[] args) {

        final int START = 2000000000;

        int counter = 0;
        for (float f = START; f < START + 50; f++)
            counter++;
        System.out.println(counter);
    }
}

</pre>


The loop variable is a float rather than the traditional int.
The initial value of f is close to Integer.MAX_VALUE, so it requires 31 bits to express precisely, 
and the float type provides only 24 bits of precision. Incrementing such a large float value will not change it. 

If, however, you ran the program, you found that it doesn't loop indefinitely; 
in fact, it terminates immediately, printing 0. What gives?


The initial value of f is so large that adding 50 to it and converting the result to a float produces 
the same value as simply converting f to a float. In other words, (float)2000000000 == 2000000050, 
so the expression f < START + 50 is false before the loop body has executed even once, 
and the loop body never gets a chance to run.


Fixing this program is as simple as changing the type of the loop variable from a float to an int. 
This avoids all the imprecision associated with floating point computation :

<pre>
for (int i = START; i < START + 50; i++)
    count++;
</pre>


The key is to observe that 2,000,000,000 has ten factors of 2: It begins with a 2 and has nine factors of 10, 
each of which is 5 x 2. This means that the binary representation of 2,000,000,000 ends in ten 0s. 
The binary representation of 50 requires only six bits, so adding 50 to 2,000,000,000 doesn't influence any bit 
higher than the sixth from the right. In particular, the seventh and eighth bits from the right are still 0. 
Promoting this 31-bit int to a float with 24 bits of precision rounds between the seventh and eighth bits, 
which simply discards the rightmost seven bits. The rightmost six bits are the only ones on which 
2,000,000,000 and 2,000,000,050 differ, so their float representations are identical.


The moral of this puzzle is simple: Do not use floating-point loop indices, because it can lead to unpredictable behavior. 
If you need a floating-point value in the body of a loop, take the int or long loop index and convert it to a float or double.
You may lose precision when converting anintorlongto afloator a long to a double, but at least it will not affect the 
loop itself. When you use floating-point, use double rather than float unless you are certain that float provides enough
precision and you have a compelling performance need to use float. The times when it's appropriate to use float rather 
than double are few and far between.

