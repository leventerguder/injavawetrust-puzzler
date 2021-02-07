# Puzzle 30: Son of Looper

Provide a declaration for i that turns this loop into an infinite loop:
<pre>
while (i != i + 0) {
}

</pre>

Unlike previous loopers, you must not use floating-point in your answer. 
In other words, you must not declare i to be of type double or float.

After all, a number is always equal to itself plus 0, and you were forbidden from using floating-point, 
so you can't use NaN. There is no NaN equivalent for the integral types. What gives?

The + operator is overloaded: For the String type, it performs not addition but string concatenation. 
If one operand in the concatenation is of some type other than String, that operand is converted to a 
string prior to concatenation [JLS 15.18.1].

In summary, operator overloading can be very misleading. 
The plus sign in the puzzle looks like addition, but it is made to perform string concatenation 
by choosing the correct type for the variable i, which is String. 
The puzzle is made even more misleading because the variable is named i,
a name that is usually reserved for integer variables. Good variable, method, 
and class names are at least as important to program readability as good comments.

Perhaps the + operator should not have been overloaded for string concatenation. 
It may well be worth providing a string concatenation operator, but it doesn't have to be +.
