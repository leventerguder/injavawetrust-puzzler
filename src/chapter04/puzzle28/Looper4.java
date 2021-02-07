package chapter04.puzzle28;

public class Looper4 {

    public static void main(String[] args) {

        /*
        In fact, you don't have to initialize i to infinity to make the loop spin forever.
        Any sufficiently large floating-point value will do;
         */

        double i = 1.0e40;
        while (i == i + 1) {
            System.out.println("infinity loop!");
        }

        // infinity == infinity +1 true


    }
}
