package chapter08.puzzle72;


class Jeopardy {
    public static final String PRIZE = "$64,000";
}


public class DoubleJeopardy extends Jeopardy {

    public static final String PRIZE = "2 cents";

    public static void main(String[] args) {
        System.out.println(DoubleJeopardy.PRIZE);
    }
}
