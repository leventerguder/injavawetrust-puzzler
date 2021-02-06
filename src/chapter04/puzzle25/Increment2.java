package chapter04.puzzle25;

public class Increment2 {

    public static void main(String[] args) {

        int j = 0;
        for (int i = 0; i < 100; i++) {
            //j = j++; equivalent  ;

            // broken too.s
            int temp = j;
            j = j + 1;
            j = temp;
        }

        System.out.println(j); // 0

    }
}
