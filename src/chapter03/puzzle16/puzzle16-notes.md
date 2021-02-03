# Puzzle 16: Line Printer

The line separator is the name given to the character or characters used to separate lines of text, and varies from 
platform to platform. On Windows, it is the CR character (carriage return) followed by the LF character (linefeed). 

On UNIX, it is the LF character alone, often referred to as the newline character. 
sThe following program passes this character to println. What does it print? Is its behavior platform dependent?

<pre>
public class LinePrinter {
    public static void main(String[] args) {

        // Note: \u000A is Unicode representation of linefeed (LF)

        char c = 0x000A;
        System.out.println(c);
    }
}

</pre>

The behavior of this program is platform independent: It won't compile on any platform


The key to this puzzle is the comment on the third line of the program. 
Like the best of comments, this one is true. Unfortunately, this one is a bit too true. 
The compiler not only translates Unicode escapes into the characters they represent before 
it parses a program into tokens (Puzzle 14), but it does so before discarding comments and white space [JLS 3.2].

The easiest way to fix the program is to remove the Unicode escape from the comment, 
but a better way is to initialize c with an escape sequence instead of a hex integer literal, obviating the need for the comment:

<pre>
public class LinePrinter {
    public static void main(String[] args) {
    char c = '\n';
        System.out.println(c);
    }
}

Once this has been done, the program will compile and run, but it's still a questionable program. 
It is platform dependent for exactly the reason suggested in the puzzle. 
On certain platforms, such as UNIX, it will print two complete line separators; on others, such as Windows, it won't. 
Although the output may look the same to the naked eye, it could easily cause problems 
if it were saved in a file or piped to another program for subsequent processing.

</pre>

If you want to print two blank lines, you should invoke println twice. 
As of release 5.0, you can use printf instead of println, with the format string "%n%n". 
Each occurrence of the characters %n will cause printf to print the appropriate platform-specific line separator.
Hopefully, the last three puzzles have convinced you that Unicode escapes can be thoroughly confusing. 

The lesson is simple: Avoid Unicode escapes except where they are truly necessary. They are rarely necessary.
