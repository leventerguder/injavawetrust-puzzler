package chapter03.puzzle23;

import java.util.Random;

public class Rhymes {

    private static Random rnd = new Random();

    public static void main(String[] args) {

        StringBuffer word = null;

        switch (rnd.nextInt(2)) {
            case 1:
                word = new StringBuffer('P');
            case 2:
                word = new StringBuffer('G');
            default:
                word = new StringBuffer('M');
        }

        word.append('a');
        word.append('i');
        word.append('n');
        System.out.println(word);


        // rnd.nextInt(3)
        // break;s
        // StringBuffer(int capacity) , should be  StringBuffer(String str)
        /*
        To summarize: First, be careful of fencepost errors.
        Second, remember to put a break after each case in switch statements.
        Third, use common idioms and APIs, and consult the documentation when you stray from the well-worn path.
        Fourth, a char is not a String but is more like an int. Finally, watch out for sneaky puzzlers.
         */
    }
}
