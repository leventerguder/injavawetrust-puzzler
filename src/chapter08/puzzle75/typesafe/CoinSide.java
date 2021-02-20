package chapter08.puzzle75.typesafe;

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
