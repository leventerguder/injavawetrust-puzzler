# Puzzle 9: Tweedledum

Provide declarations for the variables x and i such that this is a legal statement:

<pre>
x += i;
</pre>

but this is not 
<pre>

x = x + i;
</pre>


Many programmers think that the first statement in this puzzle (x += i) is simply a shorthand for the second (x = x + i).
This isn't quite true.
Both of these statements are assignment expressions [JLS 15.26]. 
The second statement uses the simple assignment operator (=), whereas the first uses a compound assignment operator

compound assignment expressions automatically cast the result of the computation they perform to 
the type of the variable on their left-hand side. If the type of the result is identical to the type of the variable, 
the cast has no effect. If, however, the type of the result is wider than that of the variable, the compound assignment
operator performs a silent narrowing primitive conversion [JLS 5.1.3]. Attempting to perform the equivalent simple 
assignment would generate a compilation error, with good reason

It should be apparent that compound assignment expressions can be dangerous. 
To avoid unpleasant surprises, do not use compound assignment operators on variables of type byte, short, or char.

When using compound assignment operators on variables of type int, ensure that the expression 
on the right-hand side is not of type long, float, or double. 

When using compound assignment operators on variables of type float, 
ensure that the expression on the right-hand side is not of type double. 

In summary, compound assignment operators silently generate a cast. 
If the type of the result of the computation is wider than that of the variable, 
the generated cast is a dangerous narrowing cast. Such casts can silently discard precision or magnitude. 
For language designers, it is probably a mistake for compound assignment operators to generate invisible casts; 
compound assignments where the variable has a narrower type than the result of the computation should probably be illegal.
