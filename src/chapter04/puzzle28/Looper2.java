package chapter04.puzzle28;

public class Looper2 {

    public static void main(String[] args) {

        double i = 1.0 / 0;
        while (i == i + 1) {
            System.out.println("infinity loop!");
        }

        // infinity == infinity +1 true


    }
}
