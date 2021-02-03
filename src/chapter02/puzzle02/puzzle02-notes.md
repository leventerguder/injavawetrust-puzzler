# Puzzle 2: Time for a Change
What does it print?

<pre>
public class Change {
    public static void main(String args[]) {
        System.out.println(2.00 - 1.10);
    }
}
</pre>


Naively, you might expect the program to print 0.90, but how could it know that you wanted two digits after the decimal point? 
If you know something about the rules for converting double values to strings, which are specified by the documentation 
for Double.toString [Java-API], you know that the program prints the shortest decimal fraction sufficient to distinguish
the double value from its nearest neighbor, with at least one digit before and after the decimal point. 
It seems reasonable, then, that the program should print 0.9. Reasonable, perhaps, but not correct. 
If you ran the program, you found that it prints 0.8999999999999999.

The problem is that the number 1.1 can't be represented exactly as a double, 
so it is represented by the closest double value. The program subtracts this value from 2. 
Unfortunately, the result of this calculation is not the closest double value to 0.9. 

you might be tempted to fix the program by using the printf facility to set the precision of the output:

<pre>

// Poor solution - still uses binary floating-point!
System.out.printf("%.2f%n", 2.00 - 1.10);

</pre>

This prints the right answer but does not represent a general solution to the underlying problem: 
It still uses double arithmetic, which is binary floating-point. Floating-point arithmetic provides good approximations
over a wide range of values but does not generally yield exact results.

Binary floating-point is particularly ill-suited to monetary calculations, as it is impossible to represent 0.1 or 
any other negative power of 10 exactly as a finite-length binary fraction 

One way to solve the problem is to use an integral type, such as int or long, and to perform the computation in cents. 
If you go this route, make sure the integral type is large enough to represent all the values you will use in your program.

System.out.println((200 - 110) + " cents");


Another way to solve the problem is to use BigDecimal, which performs exact decimal arithmetic. 
It also interoperates with the SQL DECIMAL type via JDBC. 
There is one caveat: Always use the BigDecimal(String) constructor, never BigDecimal(double). 
The latter constructor creates an instance with the exact value of its argument: new BigDecimal(.1) 
returns a BigDecimal representing 0.1000000000000000055511151231257827021181583404541015625. 


This version is not terribly pretty, as Java provides no linguistic support for BigDecimal. 
Calculations with BigDecimal are also likely to be slower than those with any primitive type, 
which might be an issue for some programs that make heavy use of decimal calculations. 
It is of no consequence for most programs.

In summary, avoid float and double where exact answers are required; for monetary calculations, use int, long, or BigDecimal. 
For language designers, consider providing linguistic support for decimal arithmetic. 
One approach is to offer limited support for operator overloading, 
sso that arithmetic operators can be made to work with numerical reference types, such as BigDecimal.

