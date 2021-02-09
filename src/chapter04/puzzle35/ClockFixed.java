package chapter04.puzzle35;

public class ClockFixed {

    public static void main(String[] args) {

        /*
        The easiest way to fix the program is to insert a pair of parentheses into the boolean expression
        to force the correct order of evaluation:
         */
        int minutes = 0;
        for (int ms = 0; ms < 60 * 60 * 1000; ms++)
            if (ms % (60 * 1000) == 0) //bugfixed
                minutes++;
        System.out.println(minutes);

    }
}
