package chapter02.puzzler09;

public class Tweedledum {

    public static void main(String[] args) {

        short x = 0;
        int i = 123456;

        x += i; // Contains a hidden cast!  //
        System.out.println(x); // -7616s

        //x = x + i; // Won't compile - "possible loss of precision"


        /*
        It should be apparent that compound assignment expressions can be dangerous.
        To avoid unpleasant surprises, do not use compound assignment operators on variables of type byte, short, or char.
         */

        // extra examples
        int j = 1000;
        byte b = 0;
        b += j;
        System.out.println(b); // -24

        /*
        When using compound assignment operators on variables of type float,
        ensure that the expression on the right-hand side is not of type double
         */
        float f = 0;
        double d = 11.123456789;
        f += d;
        System.out.println(f); // 11.123457
    }

}
