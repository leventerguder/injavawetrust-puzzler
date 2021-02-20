package chapter08.puzzle75;

class Tails extends CoinSide {
    private Tails() {
    }

    public static final Tails INSTANCE = new Tails();

    public String toString() {
        return "tails";
    }
}
