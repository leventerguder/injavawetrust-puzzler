# Puzzle 29: Bride of Looper

Provide a declaration for i that turns this loop into an infinite loop:
<pre>
while (i != i) {
}
</pre>

This looper is perhaps even more puzzling than the previous one. 
It really seems that it ought to terminate immediately, no matter what declaration precedes it. 
A number is always equal to itself, right?


Right, but IEEE 754 floating-point arithmetic reserves a special value to represent
a quantity that is not a number [IEEE-754]. This value, known as NaN (short for "Not a Number"), is the value of all 
floating-point computations that do not have well-defined numeric values, such as 0.0 / 0.0. 
The specification says that NaN is not equal to any floating-point value, including itself [JLS 15.21.1]. 
Therefore, if i is initialized to NaN before the loop starts, the termination test (i != i) evaluates to true, 
and the loop never terminates. Strange but true.

You can initialize i with any floating-point arithmetic expression that evaluates to NaN; for example:

<pre>
double i = 0.0 / 0.0;
</pre>

Once again, you can add clarity by using a constant that is provided for you by the standard libraries:

<pre>
double i = Double.NaN;
</pre>

The principle underlying the rules for computing with NaN is that once it generates NaN, 
a computation is damaged, and no further computation can repair the damage.
The NaN value is intended to allow a damaged computation to proceed to a point where it is convenient 
to deal with the situation.


In summary, the float and double types have a special NaN value to represent a quantity that is not a number. 
The rules for computations involving NaN are simple and sensible, 
but the consequences of these rules can be counterintuitive.
