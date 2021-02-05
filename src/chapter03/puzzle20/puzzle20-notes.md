# Puzzle 20: What's My Class?

This program was designed to print the name of its class file.
What does the program print?

<pre>
package com.javapuzzlers;
public class Me {
   public static void main(String[] args) {
      System.out.println(
         Me.class.getName().replaceAll(".", "/") + ".class");
    } 
}
</pre>

The program appears to obtain its class name ("com.javapuzzlers.Me"), replace all occurrences of the string"."with"/", 
and append the string".class". You might think that the program would print com/javapuzzlers/Me.class, 
which is the class file from which it was loaded. If you ran the program, 
you found that it actually prints ///////////////////.class.

The problem is that String.replaceAll takes a regular expression as its first parameter, not a literal sequence of characters.
The regular expression "." matches any single character, and so every character of the class name is replaced by a slash,
producing the output we saw.


To match only the period character, the period in the regular expression 
must be escaped by preceding it with a backslash (\).
Because the backslash character has special meaning in a string literal it begins an escape sequence 
the backslash itself must be escaped with a second backslash. This produces an escape sequence that generates 
a backslash in the string literal.

<pre>
System.out.println(Me.class.getName().replaceAll("\\.", "/") + ".class");
</pre>

To solve this kind of problem, release 5.0 provides the new static method java.util.regex.Pattern.quote. 
It takes a string as a parameter and adds any necessary escapes, returning a regular expression string 
that matches the input string exactly.

<pre>
System.out.println(Me.class.getName().replaceAll(Pattern.quote("."), "/") + ".class");
</pre>

Another problem with this program is that the correct behavior is platform dependent. 
Not all file systems use the slash character to separate hierarchical filename components. 
To get a valid filename for the platform on which you are running, you should use the 
correct platform-dependent separator character in place of the slash. That is exactly what the next puzzle does.
