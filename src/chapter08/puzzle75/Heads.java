package chapter08.puzzle75;

class Heads extends CoinSide {
    private Heads() {
    }

    public static final Heads INSTANCE = new Heads();

    public String toString() {
        return "heads";
    }
}
