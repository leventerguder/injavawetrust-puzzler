package chapter08.puzzle75.enums;

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
