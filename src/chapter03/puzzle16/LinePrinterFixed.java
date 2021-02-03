package chapter03.puzzle16;

public class LinePrinterFixed {

    public static void main(String[] args) {
        /*
        The easiest way to fix the program is to remove the Unicode escape from the comment,
        but a better way is to initialize c with an escape sequence instead of a hex integer literal, obviating the need for the comment:
         */
        char c = '\n';
        System.out.println(c);
    }

    /*
    Once this has been done, the program will compile and run, but it's still a questionable program.
    It is platform dependent for exactly the reason suggested in the puzzle. On certain platforms, such as UNIX,
    it will print two complete line separators; on others, such as Windows, it won't.
    Although the output may look the same to the naked eye, it could easily cause problems
    if it were saved in a file or piped to another program for subsequent processing.

     */

    /*
    If you want to print two blank lines, you should invoke println twice.
    As of release 5.0, you can use printf instead of println, with the format string "%n%n".
    Each occurrence of the characters %n will cause printf to print the appropriate platform-specific line separator.
     */
}
