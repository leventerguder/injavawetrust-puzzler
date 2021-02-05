package chapter03.puzzle23;

import java.util.Random;

/*
Here is a more elegant version:
 */
public class RhymesFixed2 {

    private static Random rnd = new Random();

    public static void main(String args[]) {
        System.out.println("PGM".charAt(rnd.nextInt(3)) + "ain");
    }

}
