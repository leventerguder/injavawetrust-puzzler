package chapter03.puzzle23;

import java.util.Random;

/*
Here is a more elegant version:
 */
public class RhymesFixed3 {

    private static Random rnd = new Random();

    public static void main(String args[]) {
        String a[] = {"Main", "Pain", "Gain"};
        System.out.println(randomElement(a));
    }

    private static String randomElement(String[] a) {
        return a[rnd.nextInt(a.length)];
    }

}
