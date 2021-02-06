package chapter04.puzzle24;

public class BigDelight {

    public static void main(String[] args) {

        /*
        Mixed-type comparisons are always confusing because the system is forced to promote one operand to match the
        type of the other. The conversion is invisible and may not yield the results that you expect.
        */

        for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
            if (b == 0x90)
                System.out.print("Joy!");

            // 0x90 is hexadecimal number.
            // in decimal it is 144. 144 is out of byte's bound.
        }


    }



}
