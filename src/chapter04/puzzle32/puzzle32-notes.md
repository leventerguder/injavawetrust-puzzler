# Puzzle 32: Curse of Looper

Provide declarations for i and j that turn this loop into an infinite loop:

<pre>
while (i <= j && j <= i && i != j) {

}
</pre>

If i <= j and j <= i, surely i must equal j? This property certainly holds for the real numbers. 

To make this concrete, the following declarations give the expression (i <= j && j <= i && i != j) the value true, 
turning the loop into an infinite loop:

<pre>

Integer i = new Integer(0);

Integer j = new Integer(0);
</pre>


The first two subexpressions (i <= j and j <= i) perform unboxing conversions [JLS 5.1.8] on i and j and compare 
the resulting int values numerically. Both i and j represent 0, so both of these subexpressions evaluate to true. 
The third subexpression (i != j) performs an identity comparison on the object references i and j. 
The two variables refer to distinct objects, as each was initialized to a new Integer instance. 
Therefore, the third subexpression also evaluates to true, and the loop spins forever.


In summary, there is a fundamental difference in the way numerical comparison operators and equality operators behave 
when both operands are of boxed numeric types: Numerical comparison operators perform value comparisons, while equality
operators perform reference identity comparisons.

For language designers, life might have been simpler and more pleasant 
if the equality operators had always performed value comparisons (Puzzle 13). 
