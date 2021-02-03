# Puzzle 4: It's Elementary

What does it print?

<pre>

public class Elementary {
    public static void main(String[] args) {
        System.out.println(12345 + 5432l);
    }
}
</pre>


When you run the program, it prints 17777.

Take a careful look at the two operands of the + operator. We are adding the int value 12345 to the long value 5432l.
Note the subtle difference in shape between the digit 1 at the beginning of the left operand 
and the lowercase letter el at the end of the right operand

Always use a capital el (L) in long literals, never a lowercase el (l). 


Similarly, avoid using a lone el (l) as a variable name.

<pre>

// Bad code - uses el (l) as a variable name
List<String> l = new ArrayList<String>();
l.add("Foo");
System.out.println(1);

</pre>

In summary, the lowercase letter el and the digit 1 are nearly identical in most typewriter fonts. 
To avoid confusing the readers of your program, never use a lowercase el to terminate a long literal 
or as a variable name. Java inherited much from the C programming language, including its syntax for long literals.
ssIt was probably a mistake to allow long literals to be written with a lowercase el.
