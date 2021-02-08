package chapter04.puzzle31;

public class GhostOfLooper {

    public static void main(String[] args) {
        /*
        Provide a declaration for i that turns this loop into an infinite loop:
         */
        short i = -1;
        while (i != 0)
            i >>>= 1;

        System.out.println(i);

        /*

        Because the initial value of i ((short)0xffff) is nonzero, the body of the loop is executed.
        The first step in the execution of the shift operation is that the value of i is promoted to an int.
        All arithmetic operations do this to operands of type short, byte, or char.
        This promotion is a widening primitive conversion, so no information is lost.
        This promotion performs sign extension, so the resulting int value is 0xffffffff.
        This value is then shifted to the right by one bit without sign extension to yield the
        int value 0x7fffffff. Finally, this value is stored back into i. In order to store the
        int value into the short variable, Java performs the dreaded narrowing primitive conversion,
        which simply lops off the high-order 16 bits of the value. This leaves (short)0xffff,
        and we are back where we started. The second and successive iterations of the loop behave identically,
        so the loop never terminates.
         */
    }
}

