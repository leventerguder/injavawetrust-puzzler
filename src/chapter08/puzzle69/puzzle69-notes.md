# Puzzle 69: Fade to Black

Suppose that you can't modify classes X and C in the previous puzzle (Puzzle 68). 
Can you write a class whose main method reads the value of the field Z in class X.Y and prints it? Do not use reflection.

<pre>

public class FadeToBlack {
    public static void main(String[] args) {

        System.out.println(((X.Y) null).Z);
        // System.out.println(X.Y.Z);
    }
}

class X {
    static class Y {
        static String Z = "Black";
    }
    static C Y = new C();
}

class C {
    String Z = "White";
}

</pre>

You can also solve this puzzle without resorting to questionable practices, 
by using the obscured class in the extends clause of a class declaration.


<pre>
public class FadeToBlack {

    static class Xy extends X.Y {
    }

    public static void main(String[] args) {
        System.out.println(Xy.Z);
    }
}
</pre>

you can also solve the puzzle by using X.Y in the extends clause of a type variable declaration:

<pre>
    public static <T extends X.Y> void main(String[] args) {
        System.out.println(T.Z);
    }
</pre>


In summary, to solve a problem caused by the obscuring of a type by a variable, rename the type and variable 
in accordance with standard naming conventions, as discussed in Puzzle 68. If this is not possible, 
use the obscured type name in a context where only type names are allowed. With any luck, you will never have to 
resort to such contortions, as most library authors are sane enough to avoid the questionable practices that 
make them necessary. If, however, you do find yourself in this situation, it's nice to know that there is a workaround.
