# Puzzle 18: String Cheeses

This program creates a string from a sequence of bytes, then iterates over the characters in the string and prints 
them as numbers. Describe the sequence of numbers that the program prints:

<pre>
public class StringCheese {
    public static void main(String args[]) {
        byte bytes[] = new byte[256];
        for (int i = 0; i < 256; i++)
            bytes[i] = (byte) i;
        String str = new String(bytes);
        for (int i = 0, n = str.length(); i < n; i++)
            System.out.print((int) str.charAt(i) + " ");
    }
}

</pre>

First, the byte array is initialized with every possible byte value from 0 to 255. 
Then these byte values are translated into char values by the String constructor.
Finally, the char values are cast to int values and printed. 
The printed values are guaranteed to be nonnegative, because char values are unsigned, 
so you might expect the program to print the integers from 0 to 255 in order.

If you ran the program, maybe you saw this sequence. Then again, maybe you didn't.

The culprit here is the String(byte[]) constructor. Its specification says: 
"Constructs a new String by decoding the specified byte array using the platform's default charset. 
The length of the new String is a function of the charset, and hence may not be equal to the length of the byte array. 
The behavior of this constructor when the given bytes are not valid in the default charset is unspecified"

What exactly is a charset? Technically, it is "the combination of a coded character set and a character-encoding scheme"
In other words, a charset is a bunch of characters, the numerical codes that represent them, 
and a way to translate back and forth between a sequence of character codes and a sequence of bytes. 
The translation scheme differs greatly among charsets. Some have a one-to-one mapping between characters and bytes; 
most do not. The only default charset that will make the program print the integers from 0 to 255 in order is ISO-8859-1
, more commonly known as Latin-1

A J2SE Runtime Environment's default charset depends on the underlying operating system and locale. 
If you want to know your JRE's default charset and you are using release 5.0 or a later release, 
you can find out by invoking java.nio.charset.Charset.defaultCharset(). If you are using an earlier release, 
you can find out by reading the system property "file.encoding".

If you replace the String constructor invocation in the original program with the one that follows, 
the program is guaranteed to print the integers from 0 to 255 in order, regardless of the default charset:

<pre>
String str = new String(bytes, "ISO-8859-1");
</pre>

The lesson of this puzzle is that every time you translate a byte sequence to a String, you are using a charset, 
whether you specify it explicitly or not. If you want your program to behave predictably, 
specify a charset each time you use one. For API designers, perhaps it was not such a good idea to 
provide a String(byte[]) constructor that depends on the default charset.
