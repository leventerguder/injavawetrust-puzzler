package chapter08.puzzle75;

import java.util.Random;

public class CoinSide {

    private static Random rnd = new Random();

    public static CoinSide flip() {
        return rnd.nextBoolean() ?
                Heads.INSTANCE : Tails.INSTANCE;
    }

    /*
    The behavior of the conditional (? :) operator was more restrictive before release 5.0 [JLS2 15.25].
    When both the second and third operands were of a reference type,
    the conditional operator required that one of them be a subtype of the other.
    As neither class Heads nor Tails is a subtype of the other, there is an error. To get this code to compile,
    you could cast one of the operands to the common supertype:
     */
    public static CoinSide flip2() {
        return rnd.nextBoolean() ?
                (CoinSide) Heads.INSTANCE : Tails.INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(flip());
        System.out.println(flip2());
    }
}
