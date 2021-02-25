# Puzzle 87: Strained Relations

In mathematics, the equals sign (=) defines an equivalence relation on the real numbers. 
An equivalence relation partitions a set into equivalence classes, each consisting of all the values that 
are equivalent to one another. Other equivalence relations include "is congruent to" on the set of all triangles 
and "has the same number of pages as" on the set of all books. Formally, a relation ~ is an equivalence relation 
if and only if it is reflexive, transitive, and symmetric. These properties are defined as follows:

• Reflexive: x ~ x for all x. In other words, every value is related to itself.
• Transitive: if x ~ y and y ~ z, then x ~ z. In other words, if one value is related to a second and the
second is related to a third, the first value is related to the third.
• Symmetric: if x ~ y, then y ~ x. In other words, if one value is related to a second, the second value is
related to the first.

But this isn't a book about set theory; it's a book about Java. 
In Java, does the == operator define an equivalence relation over the primitive values? If not, 
which of the three properties does it violate? Provide code snippets to demonstrate any violations.

If you did Puzzle 29, you know that the == operator is not reflexive, because the expression (Double.NaN == Double.NaN) 
evaluates to false, as does the expression (Float.NaN == Float.NaN). 
But does the == operator violate symmetry or transitivity? It turns out that it does not violate symmetry: (x == y)
implies that (y == x) for all values x and y. transitivity is another matter entirely.


Puzzle 35 provides a clue as to why the == operator is not transitive over the primitive values. 
When comparing two numeric primitive values, the == operator first performs binary numeric promotion [JLS 5.6.2]. 
This may result in a widening primitive conversion on one of the two values [JLS 5.1.2]. Most widening primitive 
conversions are harmless, with three notable exceptions: Converting an int or a long value to float, or a long value 
to double can result in loss of precision. This loss of precision can manifest itself as nontransitivity of the == operator.

The trick to achieving this nontransitivity is to lose precision in two of the three value comparisons, 
resulting in false positives. This can be accomplished, for example, by choosing large but distinct long values for x and z, 
and a double value that is close to both long values for y. The following program does exactly that. 
It prints true true false, clearly demonstrating the nontransitivity of the == operator over primitives:

The lesson is: Beware of lossy widening primitive conversions to float and double. 
They are silent but deadly. They can violate your intuition and cause subtle bugs (Puzzle 34). 
More generally, beware of mixed-type operations (Puzzles 5, 8, 24, and 31). 
The lesson for language designers is the same as for Puzzle 34: Silent loss of precision confuses programmers.
