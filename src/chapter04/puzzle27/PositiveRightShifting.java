package chapter04.puzzle27;

public class PositiveRightShifting {

	public static void main(String[] args) {

		int x = Integer.MAX_VALUE;
		// 01111111 11111111 11111111 11111111
		// 2147483647

		System.out.println(x);

		System.out.println(x >> 1);
		// 00111111 11111111 11111111 11111111
		// 1073741823

		System.out.println(x >> 2);
		// 00011111 11111111 11111111 11111111
		// 536870911

		System.out.println(x >> 3);
		// 00001111 11111111 11111111 11111111
		// 268435455

		System.out.println(x >> 4);
		// 00000111 11111111 11111111 11111111
		// 134217727

		System.out.println(x >> 31);
		// 00000000 00000000 00000000 00000000
		// 0

		System.out.println(x >> 32);
		// 01111111 11111111 11111111 11111111
		// 2147483647

		System.out.println("shifting y");
		int y = 134216703;
		// 00000111 11111111 11111011 11111111

		System.out.println(y);

		System.out.println(y >> 1);
		// 00000011 11111111 11111101 11111111
		// 67108351

		System.out.println(y >> 2);
		// 00000001 11111111 11111110 11111111
		// 33554175

		System.out.println(y >> 3);
		// 00000000 11111111 11111111 01111111
		// 16777087

		// 00000111 11111111 11111011 11111111
		// 00000011 11111111 11111101 11111111
		// 00000001 11111111 11111110 11111111
		// 00000000 11111111 11111111 01111111

		System.out.println(y >> 31);
		// 00000000 00000000 00000000 00000000
		// 0

		System.out.println(y >> 32);
		// 00000111 11111111 11111011 11111111
		// 134216703
		
		System.out.println(1>>32);
	}
}
