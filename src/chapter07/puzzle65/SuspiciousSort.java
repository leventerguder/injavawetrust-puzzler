package chapter07.puzzle65;

import java.util.*;

public class SuspiciousSort {

    public static void main(String[] args) {

        Random rnd = new Random();
        Integer[] arr = new Integer[100];


        for (int i = 0; i < arr.length; i++)
            arr[i] = rnd.nextInt();

        Comparator<Integer> cmp = new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        };

        Arrays.sort(arr, cmp);
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



