# Puzzle 90: It's Absurd, It's a Pain, It's Superclass!

The following program doesn't actually do anything. Worse, it won't compile. Why not? How can you fix it?

<pre>
public class Outer {
    class Inner1 extends Outer {}
    class Inner2 extends Inner1 {}
}
</pre>
