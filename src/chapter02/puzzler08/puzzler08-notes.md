# Puzzle 8: Dos Equis

What does the following program print?

<pre>

public class DosEquis {
    public static void main(String[] args) {
        char x = 'X';
        int i = 0;
        System.out.print(true  ? x : 0);
        System.out.print(false ? i : x);
    } 
}

</pre>

The first print statement evaluates the conditional expression (true ? x : 0) and prints the result. The result is the 
value of the char variable x, which is 'X'.
The second print statement evaluates the conditional expression (false ? i : x) and prints the result. 
Again the result is the value of x, which is still 'X', so the program ought to print XX

If you ran the program, however, you found that it prints X88. This behavior seems strange. 
The first print statement prints X and the second prints 88. What accounts for their different behavior?


Note that the types of the second and third operands are different from each other 
in both of the conditional expressions: x is of type char, whereas 0 and i are both of type int. 
As mentioned in the solution to Puzzle 5, mixed-type computation can be confusing

1. If the second and third operands have the same type, that is the type of the conditional expression. 
   In other words, you can avoid the whole mess by steering clear of mixed-type computation.
2. If one of the operands is of type T where T is byte, short, or char and the other operand 
   is a constant expression of type int whose value is representable in type T, 
   the type of the conditional expression is T.
3. Otherwise, binary numeric promotion is applied to the operand types, 
   and the type of the conditional expression is the promoted type of the second and third operands.


In both expressions, the value of the int operand is 0, which is representable as a char. 
Only the int operand in the first expression, however, is constant (0); 
the int operand in the second expression is variable (i). 
Therefore, point 2 applies to the first expression and its return type is char. 

Point 3 applies to the second conditional expression, and its return type is the result of applying 
binary numeric promotion to int and char, which is int [JLS 5.6.2].

For the first expression, PrintStream.print(char) is invoked; for the second, PrintStream.print(int).


Putting the final modifier on the declaration for i would turn i into a constant expression, 
causing the program to print XX, but it would still be confusing. To eliminate the confusion, 
it is best to change the type of i from int to char, avoiding the mixed-type computation.

In summary, it is generally best to use the same type for the second and third operands in conditional expressions. 
Otherwise, you and the readers of your program must have a thorough understanding of the complex specification 
for the behavior of these expressions.
