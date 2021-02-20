# Puzzle 72: Final Jeopardy

What does this program do?

<pre>
class Jeopardy {
    public static final String PRIZE = "$64,000";
}


public class DoubleJeopardy extends Jeopardy {

    public static final String PRIZE = "2 cents";

    public static void main(String[] args) {
        System.out.println(DoubleJeopardy.PRIZE);
    }
}

</pre>

If you tried the program, you found that it compiles without a hitch and prints 2 cents. What went wrong?

It turns out that the final modifier means something completely different on methods and fields. On a method, 
final means that the method may not be overridden (for instance methods) or hidden (for static methods) [JLS 8.4.3.3]. 
On a field, final means the field may not be assigned more than once [JLS 8.3.1.2]. 
The keyword is the same, but the behavior is unrelated.

If you want to guarantee the prize in the Jeopardy class even while preserving the ability to subclass it, 
use a final method instead of a final f

<pre>
class Jeopardy {
    private static final String PRIZE = "$64,000";
    public static final String prize() {
        return PRIZE;
    } 
}
</pre>

In summary, avoid reusing names for unrelated variables or unrelated concepts. 
Using distinct names for unrelated concepts helps readers and programmers to keep the concepts separate.
