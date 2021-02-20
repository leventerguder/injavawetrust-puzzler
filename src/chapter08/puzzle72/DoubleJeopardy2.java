package chapter08.puzzle72;

class Jeopardy2 {

    private static final String PRIZE = "$64,000";

    public static final String prize() {
        return PRIZE;
    }
}


public class DoubleJeopardy2 extends Jeopardy2 {

    public static final String PRIZE = "2 cents";

    public static void main(String[] args) {
        System.out.println(DoubleJeopardy2.PRIZE);
        System.out.println(Jeopardy2.prize());
        System.out.println(DoubleJeopardy2.prize());
    }
}

/*
In summary, avoid reusing names for unrelated variables or unrelated concepts.
Using distinct names for unrelated concepts helps readers and programmers to keep the concepts separate.
 */
