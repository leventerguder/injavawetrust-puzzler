package chapter04.puzzle28;

public class Looper3 {

    public static void main(String[] args) {

        double i = Double.POSITIVE_INFINITY;
        while (i == i + 1) {
            System.out.println("infinity loop!");
        }

        // infinity == infinity +1 true
        

    }
}
