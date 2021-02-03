package chapter02.puzzle06;

public class Multicast {
    public static void main(String[] args) {

		/*
		The cast from byte to char is trickier because byte is a signed type and char unsigned.
		It is usually possible to convert from one integral type to a wider one while preserving numerical value,
		but it is impossible to represent a negative byte value as a char.
		 */
        System.out.println((int) (char) (byte) -1);

    }
}
