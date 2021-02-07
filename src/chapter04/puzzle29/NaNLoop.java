package chapter04.puzzle29;

public class NaNLoop {

    public static void main(String[] args) {

        double number = Double.NaN;
        while (number != number) {
            System.out.println("infinite loop");
        }

    }
}
