# Puzzle 11: The Last Laugh

What does the following program print?s

<pre>

public class LastLaugh {
    public static void main(String args[]) {
        System.out.print("H" + "a");
        System.out.print('H' + 'a');
    }
}

</pre>


If you ran the program, you found that it prints Ha169. Now why would it do a thing like that?


As expected, the first call to System.out.print prints Ha: Its argument is the expression "H" + "a", which performs 
the obvious string concatenation. The second call to System.out.print is another story. 
Its argument is the expression 'H' + 'a'. The problem is that 'H' and 'a' are char literals. 
Because neither operand is of type String, the + operator performs addition rather than string concatenation.

The compiler evaluates the constant expression 'H' + 'a' by promoting each of the char-valued operands ('H' and 'a') 
to int values through a process known as widening primitive conversion [JLS 5.1.2, 5.6.2].

Widening primitive conversion of a char to an int zero extends the 16-bit char value to fill the 32-bit int. 
In the case of 'H', the char value is 72 and in the case of 'a', it is 97, so the expression 'H' + 'a' is equivalent 
to the int constant 72 + 97, or 169.


So how do you concatenate characters? You could use the libraries. For example, you could use a StringBuffer :

<pre>
StringBuffer sb = new StringBuffer();
sb.append('H');
sb.append('a');
System.out.println(sb);
</pre>

This works, but it's ugly. There are ways to avoid the verbosity of this approach. 
You can force the + operator to perform string concatenation rather than addition by ensuring that 
at least one of its operands is a string. The common idiom is to begin a sequence of concatenations 
with the empty string (""), as follows:

<pre>
System.out.print("" + 'H' + 'a');
</pre>


you also have the option of using the printf facility:

<pre>
System.out.printf("%c%c", 'H', 'a');
</pre>


This puzzle also contains a lesson for language designers. 
Operator overloading, even to the limited extent that it is supported in Java, can be confusing. 
sIt may have been a mistake to overload the + operator for string concatenation.
