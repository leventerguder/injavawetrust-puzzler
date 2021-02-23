# Puzzle 81: Charred Beyond Recognition

What does the program print?

<pre>
public class Greeter {

    public static void main(String[] args) {

        String greeting = "Hello world";
        for (int i = 0; i < greeting.length(); i++)
            System.out.write(greeting.charAt(i));

    }
}

</pre>

The problem is that System.out is buffered. The characters in Hello world were written to the buffer for System.out, 
but the buffer was never flushed. Most programmers believe that System.out and System.err flush 
themselves automatically whenever output is performed.

The streams referenced by System.out and System.err are indeed instances of the automatically flushing variant 
of PrintStream, but no mention is made of the write(int) method in the above documentation. 
The documentation for write(int) says: "Write the specified byte to this stream. 
If the byte is a newline and automatic flushing is enabled then the flush method will be invoked" [Java-API].
In practice, write(int) is the only output method that does not flush a PrintStream on which automatic flushing is enabled.

Curiously, if the program is modified to use print(char) instead of write(int), 
it flushes System.out and prints Hello world. This behavior contradicts the documentation for print(char), 
which says [Java-API]:

Print a character. The character is translated into one or more bytes according to the platform's 
default character encoding, and these bytes are written in exactly the manner of the write(int) method.

Similarly, if the program is modified to use print(String), it flushes the stream even though the documentation prohibits it. 
The documentation should almost certainly be changed to describe the actual behavior; it would be too destabilizing to change the behavior.

The simplest change that fixes the program is to add a System.out.flush invocation after the loop. 
If this change is made, the program prints Hello world as expected. It would, however, be far better to 
rewrite the program to use the more familiar System.out.println idiom for producing output on the console.
