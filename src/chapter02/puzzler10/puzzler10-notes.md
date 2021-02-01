# Puzzle 10: Tweedledee

provide declarations for the variables x and i such that this is a legal statement:

<pre>
x = x + i;
</pre>

but this is not :

<pre>
x += i;
</pre>

At first glance, this puzzle might appear to be the same as the previous one. Rest assured, it's different. 
The two puzzles are opposite in terms of which statement must be legal and which must be illegal.

Like the previous puzzle, this one depends on the details of the specification for compound assignment operators.
That is where the similarity ends. Based on the previous puzzle, you might think that compound assignment operators 
are less restrictive than the simple assignment operator. 
This is generally true, but the simple assignment operator is more permissive in one area.


Compound assignment operators require both operands to be primitives, such as int, or boxed primitives, such as Integer,
with one exception: The += operator allows its right-hand operand to be of any type if the variable 
on the left-hand side is of type String, in which case the operator performs string concatenation [JLS 15.26.2].

The simple assignment operator (=) is much less picky when it comes to allowing object reference types 
on the left-hand side: You can use them to your heart's content so long as the expression 
on the right-hand side is assignment compatible with the variable on the left [JLS 5.2].


You can exploit this difference to solve the puzzle. 
To perform string concatenation with the += operator, you must declare the variable on its 
left-hand side to be of type String. Using the simple assignment operator, the results of a string concatenation 
can be stored in a variable of type Object.
