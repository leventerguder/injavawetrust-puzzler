# Puzzle 74: Identity Crisis

This program is incomplete. It lacks a declaration for Enigma, a class that extends java.lang.Object. 
Provide a declaration for Enigma that makes the program print false:

<pre>
public class Conundrum {
    public static void main(String[] args) {
        Enigma e = new Enigma();
        System.out.println(e.equals(e));
    }
}

</pre>

Oh, and one more thing: You must not override equals.

<pre>
final class Enigma {
    // Don't do this!
    public boolean equals(Enigma other) {
        return false;
    }
}
</pre>


There is, however, a solution that doesn't violate this advice:

<pre>
final class Enigma {
    public Enigma() {
        System.out.println(false);
        System.exit(0);
    }
}

</pre>

Arguably, this solution violates the spirit of the puzzle: 
The println invocation that produces the desired output appears in the Enigma constructor, not the main method.
Still, it does solve the puzzle, and you have to admit it's cute.

