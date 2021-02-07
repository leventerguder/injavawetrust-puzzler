package chapter04.puzzle27;

public class ShiftyFixed {

    public static void main(String[] args) {

        int distance = 0;
        for (int val = -1; val != 0; val <<= 1)
            distance++;
        System.out.println(distance);


        System.out.println(-1<<30);
        System.out.println(-1<<31);
        System.out.println(-1<<32);
        System.out.println(-1<<33);
    }
}
