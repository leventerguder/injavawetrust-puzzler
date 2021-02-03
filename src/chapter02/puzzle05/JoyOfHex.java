package chapter02.puzzle05;

public class JoyOfHex {
    public static void main(String[] args) {

        System.out.println(Long.toHexString(0x100000000L + 0xcafebabe));

        /*
        The right operand of the addition, 0xcafebabe, is promoted to the long value 0xffffffffcafebabeL.
        This value is then added to the left operand, which is 0x100000000L

        0xffffffffcafebabeL
        0x0000000100000000L
        + -------------------
        0x00000000cafebabeL
         */
        System.out.println(0x100000000L + 0xcafebabe);

        /*
        Not so for hexadecimal and octal literals.
        They can take on both positive and negative values.
        Hex and octal literals are negative if their high-order bit is set.
        In this program, the number 0xcafebabe is an int constant with its high-order bit set, so it is negative.
        It is equivalent to the decimal value -889275714.
         */

        // hexadecimal literals start 0X or 0x
        // a/A 10
        // b/B 11
        // c/C 12
        // d/D 13
        // e/E 14
        // f/F 15
        System.out.println("Hexadecimal test");
        System.out.println(0xa);
        System.out.println(0xB);
        System.out.println(0xC);
        System.out.println(0x10);
        System.out.println(0x123456);
        System.out.println(0xcafebabe); // -889275714
        System.out.println(0xffffffffcafebabeL); // -889275714
        System.out.println(0xcafebabe == 0xffffffffcafebabeL);

    }
}

