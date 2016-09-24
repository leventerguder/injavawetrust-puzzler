package _20.twos.complement;

public class NegativeRightShifting {

	public static void main(String[] args) {

		int min = Integer.MIN_VALUE;
		// -2147483648
		// 10000000 00000000 00000000 00000000

		System.out.println(min >> 1);
		// -1073741824
		// 11000000 00000000 00000000 00000000

		System.out.println(min >> 2);
		// -536870912
		// 11100000 00000000 00000000 00000000

		System.out.println(min >> 3);
		// -268435456
		// 11110000 00000000 00000000 00000000

		System.out.println(min >> 30);
		// -2
		// 11111111 11111111 11111111 11111110

		System.out.println(min >> 31);
		// -1
		// 11111111 11111111 11111111 11111111

		System.out.println(min >> 32);
		// -2147483648
		// 10000000 00000000 00000000 00000000
	}
}
