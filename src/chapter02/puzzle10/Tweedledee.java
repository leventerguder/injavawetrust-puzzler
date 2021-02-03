package chapter02.puzzle10;

public class Tweedledee {

    public static void main(String[] args) {

        Object x = "Buy ";
        String i = "Effective Java!";

        /*
        The simple assignment is legal because x + i is of type String, and String is assignment compatible with Object:
         */
        x = x + i;

        System.out.println(x);

        Object y = "Buy ";
        String j = "Effective Java!";

        // jdk6
        // The compound assignment is illegal because the left-hand side has an object reference type other than String:
        y += j;
        // it is legal after jdk7.

        System.out.println(y);

    }
}
