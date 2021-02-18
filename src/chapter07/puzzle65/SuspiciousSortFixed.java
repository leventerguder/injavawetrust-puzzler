package chapter07.puzzle65;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class SuspiciousSortFixed {

    public static void main(String[] args) {

        Random rnd = new Random();
        Integer[] arr = new Integer[100];


        for (int i = 0; i < arr.length; i++)
            arr[i] = rnd.nextInt();

        Arrays.sort(arr, Collections.reverseOrder());
        System.out.println(order(arr));

    }

    enum Order {ASCENDING, DESCENDING, CONSTANT, UNORDERED}

    ;

    static Order order(Integer[] a) {
        boolean ascending = false;
        boolean descending = false;

        for (int i = 1; i < a.length; i++) {
            ascending |= (a[i] > a[i - 1]);
            descending |= (a[i] < a[i - 1]);
        }

        if (ascending && !descending)
            return Order.ASCENDING;
        if (descending && !ascending)
            return Order.DESCENDING;
        if (!ascending)
            return Order.CONSTANT;  // All elements equal
        return Order.UNORDERED;     // Array is not sorted

    }

}



