package chapter04.puzzle33;

public class DoubleNaN {

    public static void main(String[] args) {

        System.out.println(Double.NaN == Double.NaN); // false
        System.out.println(Double.NaN == -Double.NaN); // false
        System.out.println(Double.POSITIVE_INFINITY == -Double.POSITIVE_INFINITY); // false


    }
}
