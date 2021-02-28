package chapter10.puzzle94;

import java.util.Arrays;
import java.util.Random;

public class ShuffleFixed2 {

    private static Random rnd = new Random();


    public static void shuffle(Object[] a) {
        for (int i = 0; i < a.length; i++)
            //swap(a, i, rnd.nextInt(a.length));
            swap(a, i, i + rnd.nextInt(a.length - i));
    }

    private static void swap(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        Object[] a = {"a", "b", "c"};
        shuffle(a);
        System.out.println(Arrays.toString(a));
    }

}
