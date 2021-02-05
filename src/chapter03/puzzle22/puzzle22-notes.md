# Puzzle 22: Dupe of URL

What does this program do?

<pre>
public class BrowserTest {
    public static void main(String[] args) {
        System.out.print("iexplore:");
        http://www.google.com;
        System.out.println(":maximize");
    } 
}
</pre>


This is a bit of a trick question. The program doesn't do anything special. It simply prints iexplore::maximize. 
The URL that appears in the middle of the program is a statement label [JLS 14.7] 
followed by an end-of-line comment [JLS 3.7]. Labels are rarely needed in Java, 
which thankfully lacks a goto statement. The "little-known feature of the Java programming language"
to which the puzzle refers is that you are allowed to put a label on any statement. 
This program labels an expression statement, which is legal but useless.

That said, there is no earthly reason to include the label or the comment, which has nothing to do with the program.


The lesson of this puzzle is that misleading comments and extraneous code cause confusion. 
Write comments carefully and keep them up to date. Excise dead code. 
sAlso, if something seems too strange to be true, it's probably false.
