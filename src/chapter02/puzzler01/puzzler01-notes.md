# Puzzle 1: Oddity


The following method purports to determine whether its sole argument is an odd number. Does the method work?

<pre>
public static boolean isOdd(int i) {
    return i % 2 == 1;
}
</pre>

An odd number can be defined as an integer that is divisible by 2 with a remainder of 1. The expression i % 2 computes 
the remainder when i is divided by 2, so it would seem that this program ought to work. Unfortunately, it doesn't; 

the isOdd method fails for all negative odd values. 
It returns false when invoked on any negative value, whether even or odd.


When i is a negative odd number, i % 2 is equal to -1 rather than 1, so the isOdd method incorrectly returns false. 
To prevent this sort of surprise, test that your methods behave properly when passed 
negative, zero, and positive values for each numerical parameter.


The problem is easy to fix. Simply compare i % 2 to 0 rather than to 1, and reverse the sense of the comparison:

<pre>

public static boolean isOdd(int i) {
    return i % 2 != 0;
}s
</pre>

If you are using the isOdd method in a performance-critical setting, 
you would be better off using the bitwise AND operator (&) in place of the remainder operator:

<pre>
public static boolean isOdd(int i) {
    return (i & 1) != 0;
}
</pre>

As a general rule, the divide and remainder operations are slow compared to other arithmetic and logical operations. 
It's a bad idea to optimize prematurely, but in this case, the faster version is as clear as the original, 
so there is no reason to prefer the original.

In summary, think about the signs of the operands and of the result whenever you use the remainder operator. 
The behavior of this operator is obvious when its operands are nonnegative, 
but it isn't so obvious when one or both operands are negative.
