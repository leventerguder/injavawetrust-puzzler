# Puzzle 13: Animal Farm
What does it print?
<pre>
public class AnimalFarm {

    public static void main(String[] args) {

        final String pig = "length: 10";
        final String dog = "length: " + pig.length();

        System.out.println("Animals are equal: " + pig == dog);
    }

}
</pre>

A superficial analysis of the program might suggest that it should print Animals are equal: true. 
After all, pig and dog are both final String variables initialized to the character sequence "length: 10". 
In other words, the strings referred to by pig and dog are and will forever remain equal to each other.

The == operator, however, does not test whether two objects are equal; 
it tests whether two object references are identical. 
In other words, it tests whether they refer to precisely the same object. In this case, they do not.

You may be aware that compile-time constants of type String are interned [JLS 15.28]
In other words, any two constant expressions of type String that designate 
the same character sequence are represented by identical object references. 

If initialized with constant expressions, both pig and dog would indeed refer to the same object, 
but dog is not initialized with a constant expression.

If initialized with constant expressions, both pig and dog would indeed refer to the same object, 
but dog is not initialized with a constant expression.

Well, no, actually. If you ran the program, you found that it prints false and nothing else. 
It doesn't print Animals are equal: .

Therefore, the parameter of the println method is evaluated like this:

<pre>
System.out.println("Animals are equal: " + (pig == dog));
</pre>

The value of the boolean expression is, of course, false, and that is exactly what the program prints.

Interning was designed solely to reduce the memory footprint of the virtual machine, 
not as a tool for programmers. As this puzzle demonstrates, 
it isn't always obvious which expressions will result in string constants.

Worse, if your code depends on interning for its correct operation, 
you must carefully keep track of which fields and parameters must be interned. 
The compiler can't check these invariants for you, because interned and noninterned strings 
are represented by the same type (String). 
The bugs that result from the failure to intern a string are typically quite difficult to detect.

When comparing object references, you should use the equals method in preference to 
the == operator unless you need to compare object identity rather than value. 

<pre>
System.out.println("Animals are equal: " + pig.equals(dog));
</pre>

This puzzle has two lessons for language designers. The natural precedence of string concatenation might 
not be the same as that of addition. This implies that it is problematic to overload the + operator to perform 
string concatenation, as mentioned in Puzzle 11.

One way to achieve this would be to make the == operator a shorthand for the equals method, and to provide 
a separate method to perform reference identity comparison, akin to System.identityHashCode.
