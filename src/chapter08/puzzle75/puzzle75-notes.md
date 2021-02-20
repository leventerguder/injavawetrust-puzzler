# Puzzle 75: Heads or Tails?

The behavior of this program changed between release 1.4 of the Java platform and release 5.0. 
What does the program do under each of these releases? (If you have access only to release 5.0, 
you can emulate the 1.4 behavior by compiling with the -source 1.4 flag.)

<pre>
import java.util.Random;

public class CoinSide {

    private static Random rnd = new Random();

    public static CoinSide flip() {
        return rnd.nextBoolean() ?
                Heads.INSTANCE : Tails.INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(flip());
    }
}

</pre>

<pre>
class Heads extends CoinSide {
    private Heads() {
    }

    public static final Heads INSTANCE = new Heads();

    public String toString() {
        return "heads";
    }
}

</pre>

<pre>
class Tails extends CoinSide {
    private Tails() {
    }

    public static final Tails INSTANCE = new Tails();

    public String toString() {
        return "tails";
    }
}
</pre>

The behavior of the conditional (? :) operator was more restrictive before release 5.0 [JLS2 15.25]. 
When both the second and third operands were of a reference type, 
the conditional operator required that one of them be a subtype of the other. 
As neither class Heads nor Tails is a subtype of the other, there is an error. To get this code to compile, 
you could cast one of the operands to the common supertype:

<pre>
return rnd.nextBoolean() ?
    (CoinSide)Heads.INSTANCE : Tails.INSTANCE;
</pre>

it would have been more natural to write CoinSide using the Typesafe Enum pattern [EJ Item 21]:

<pre>
import java.util.Random;

public class CoinSide {
    public static final CoinSide HEADS = new CoinSide("heads");
    public static final CoinSide TAILS = new CoinSide("tails");
    private final String name;

    private CoinSide(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    ;
    private static Random rnd = new Random();

    public static CoinSide flip() {
        return rnd.nextBoolean() ? HEADS : TAILS;
    }

    public static void main(String[] args) {
        System.out.println(flip());
    }
}

</pre>

it is natural to write CoinSide as an enum type:

<pre>
import java.util.Random;

public enum CoinSide {
    HEADS, TAILS;

    public String toString() {
        return name().toLowerCase();
    }


    private static Random rnd = new Random();

    public static CoinSide flip() {
        return rnd.nextBoolean() ? HEADS : TAILS;
    }


    public static void main(String[] args) {
        System.out.println(flip());
    }

}

</pre>
