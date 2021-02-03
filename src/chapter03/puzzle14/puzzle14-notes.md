# Puzzle 14: Escape Rout

The following program uses two Unicode escapes, which represent Unicode characters by 
their hexadecimal numeric codes. What does the program print?

<pre>
public class EscapeRout {
  public static void main(String[] args) {
    // \u0022 is the Unicode escape for double quote (")
    System.out.println("a\u0022.length() + \u0022b".length());
  }
}

</pre>

A naive analysis of the program suggests that it should print 26 because there are 
26 characters between the quotation marks that bound the string "a\u0022.length() + \u0022b". 

A deeper analysis suggests that the program should print 16, as each of the two Unicode escapes requires 
six characters in the source file but represents only one character in the string. 
The string is therefore ten characters shorter than it appears. 
Running the program tells a different story. It prints neither 26 nor 16 but 2.


The key to understanding this puzzle is that Java provides no special treatment for Unicode escapes within string literals. 
The compiler translates Unicode escapes into the characters 
they represent before it parses the program into tokens, such as strings literals [JLS 3.2]

Therefore, the first Unicode escape in the program closes a one-character string literal ("a"), 
and the second one opens a one-character string literal ("b"). 
The program prints the value of the expression "a".length() + "b".length(), or 2.


If the author of the program had actually wanted this behavior, it would have been much clearer to say:

<pre>
System.out.println("a".length() + "b".length());
</pre>


More likely, the author wanted to put the two double quote characters into the string literal. 
You can't do this with Unicode escapes, but you can do it with escape sequences [JLS 3.10.6]. 
The escape sequence representing a double quote is a backslash followed by a double quote (\"). 
If the Unicode escapes in the original program are replaced with this escape sequence, it will print 16 as expected:

<pre>
System.out.println("a\".length() + \"b".length());
</pre>


There are escape sequences for many characters, including the single quote (\'), linefeed (\n), tab (\t), and backslash (\\). 
You can use escape sequences in character literals as well as in string literals.

In summary, prefer escape sequences to Unicode escapes in string and character literals. 
Unicode escapes can be confusing because they are processed so early in the compilation sequence. 
Do not use Unicode escapes to represent ASCII characters. Inside of string and character literals, 
use escape sequences; outside of these literals, insert ASCII characters directly into the source file.
