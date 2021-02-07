package chapter04.puzzle27;

public class PositiveLeftShifting {

	public static void main(String[] args) {
		int x = 1;
		// 00000000 00000000 00000000 00000001

		System.out.println(x << 1);
		// 00000000 00000000 00000000 00000010
		// 2^1

		System.out.println(x << 2);
		// 00000000 00000000 00000000 00000100
		// 2^2

		System.out.println(x << 3);
		// 00000000 00000000 00000000 00001000
		// 2^3

		System.out.println(x << 30);
		// 01000000 00000000 00000000 00000000
		// 2^30
		// 1073741824

		System.out.println(x << 31);
		// 1000000 00000000 00000000 00000000
		// -2^31
		// -2147483648

		// -2147483648+1 = -2147483647
		// 1000000 00000000 00000000 00000001

		System.out.println(x << 32);
		// 00000000 00000000 00000000 00000001
		// 2^0
		
		System.out.println(x << 33);
		// 00000000 00000000 00000000 00000010
		// 2^1
	}
}
