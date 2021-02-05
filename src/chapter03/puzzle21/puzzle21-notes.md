# Puzzle 21: What's My Class, Take 2

This program does exactly what the one in the previous puzzle did, but doesn't assume that 
the slash character is used to separate filename components. Instead, the program uses java.io.File.separator, 
which is a public String field specified to contain the platform-specific filename separator. 
Does the program print the correct platform-specific name of the class file from which it was loaded?

<pre>

package com.javapuzzlers;
import java.io.File;
public class MeToo {
   public static void main(String[] args) {
   System.out.println(MeToo.class.getName().
       replaceAll("\\.", File.separator) + ".class");
    } 
}
</pre>

The program displays one of two behaviors depending on the underlying platform. 
If the file separator is a slash, as it is on UNIX, the program prints com/javapuzzlers/MeToo.class, which is correct. 
sIf, however, the file separator is a backslash, as it is on Windows, the program prints something like this:

<pre>
Exception in thread "main"
StringIndexOutOfBoundsException: String index out of range: 1
 at java.lang.String.charAt(String.java:558)
 at java.util.regex.Matcher.appendReplacement(Matcher.java:696)
 at java.util.regex.Matcher.replaceAll(Matcher.java:806)
 at java.lang.String.replaceAll(String.java:2000)
 at com.javapuzzlers.MeToo.main(MeToo.java:6)
</pre>


Although this behavior is platform dependent, it isn't exactly what we were looking for. 
What went wrong on Windows? It turns out that the second parameter of String.replaceAll is a not an ordinary string 
but a replacement string, as defined in the java.util.regex specification [Java-API].

A backslash appearing in a replacement string escapes the following character, causing it to be treated literally. 
When you run the program on Windows, the replacement string is a lone backslash character, which is invalid.

So how do you solve this problem? Release 5.0 provides not one but two new methods that solve it. 
One is java.util.regex.Matcher.quoteReplacement, which translates a string into the corresponding replacement string.
Here is how to fix the program by using this method:

<pre>
System.out.println(MeToo.class.getName().replaceAll("\\.",  Matcher.quoteReplacement(File.separator))+ ".class");
</pre>


The second method introduced in release 5.0 provides an even better solution. 
This method, String.replace(CharSequence, CharSequence), does the same thing as String.replaceAll, 
but treats both the pattern and the replacement as literal strings.

<pre>
System.out.println(MeToo.class.getName().replace(".", File.separator) + ".class");
</pre>

It is easier to dispense with regular expressions entirely and to use String.replace(char, char):

<pre>
System.out.println(MeToo.class.getName().replace('.', File.separatorChar) + ".class");
</pre>

The main lesson of this puzzle and the previous one is: Be careful when using unfamiliar library methods. 
When in doubt, consult the Javadoc. 
Also, regular expressions are tricky: Problems tend to show up at run time rather than compile time.

For API designers, it is important to use a method-naming scheme that distinguishes methods 
whose behavior differs in significant ways. Java's String class is not perfect in this regard. 
For many programmers, it is not easy to remember which string-replacement methods 
use literal strings and which ones use regular expressions or replacement strings.
