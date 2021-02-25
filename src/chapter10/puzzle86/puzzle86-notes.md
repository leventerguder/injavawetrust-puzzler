# Puzzle 86: Poison-Paren Litter

Can you come up with a legal Java expression that can be made illegal by parenthesizing a subexpression, 
where the added parentheses serve only to document the order of evaluation that would take place in their absence?


It seems that inserting a pair of parentheses serving only to document the existing order of evaluation should 
have no effect on the legality of a program. Indeed, this is true in nearly all cases. In two cases, however, 
inserting a seemingly innocuous pair of parentheses can make a legal Java program illegal. 
This strange state of affairs stems from the asymmetry of the two's-complement binary numbers, 
discussed in Puzzle 33 and Puzzle 64.

You may recall that the most negative int value has a magnitude that is one greater than the most positive: 
Integer.MIN_VALUE is -231, or -2,147,483,648, whereas Integer.MAX_VALUE is 231 - 1, or 2,147,483,647. 
Java does not support negative decimal literals; negative int and long constants are constructed by 
prefixing positive decimal literals with the unary minus operator (-). 
A special language rule governs this construction: The largest decimal literal of type int is 2147483648. 
Decimal literals from 0 to 2147483647 may appear anywhere an int literal may appear, but the literal 2147483648 
may appear only as the operand of the unary negation operator [JLS 3.10.1].

Once you know this rule, the puzzle is easy. The characters -2147483648 form a legal Java expression consisting of 
the unary minus operator followed by the int literal 2147483648. 
Adding a pair of parentheses to document the (trivial) order of evaluation gives -(2147483648), 
which violates the rule. Believe it or not, this program really does generate a compile-time error, 
and the error goes away if you remove the parentheses:

<pre>
public class PoisonParen {
    int i = -(2147483648);
}

</pre>


The situation for long literals is analogous. This program too generates a compile-time error that goes away 
if you remove the parentheses:

<pre>
public class PoisonParen {
    long j = -(9223372036854775808L);
}

</pre>

As for a lesson, this puzzle has none. It's a corner case, pure and simple. But you must admit, it's amusing.
