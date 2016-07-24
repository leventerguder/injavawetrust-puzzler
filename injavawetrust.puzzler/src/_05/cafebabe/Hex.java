package _05.cafebabe;

public class Hex {

	public static void main(String[] args) {
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

		System.out.println("Integer.MAX_VALUE test");

		System.out.println("MAX_VALUE     : " + Integer.MAX_VALUE);
		System.out.println("MAX_VALUE + 1 : " + (Integer.MAX_VALUE + 1)); // -2147483648
		System.out.println("MAX_VALUE + 2 : " + (Integer.MAX_VALUE + 2)); // -2147483647
		System.out.println("MAX_VALUE + 3 : " + (Integer.MAX_VALUE + 3)); // -2147483646

		System.out.println("cafebabe test");
		System.out.println(Long.toHexString(0x100000000L + 0xcafebabe));

		// 0xffffffffcafebabeL
		// 0x0000000100000000L +
		// --------------------
		// 0x00000000cafebabeL

		System.out.println(0xffffffffcafebabeL); // -889275714
		System.out.println(0xcafebabe); // -889275714

		System.out.println(Long.toHexString(0x100000000L + 0xcafebabeL));
		// 1cafebabe

	}

}
