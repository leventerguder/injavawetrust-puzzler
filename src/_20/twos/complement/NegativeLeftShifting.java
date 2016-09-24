package _20.twos.complement;

public class NegativeLeftShifting {

	public static void main(String[] args) {

		// -1
		// 11111111 11111111 11111111 11111111

		System.out.println(-1 << 1);
		// -2
		// 11111111 11111111 11111111 11111110

		System.out.println(-1 << 2);
		// -4
		// 11111111 11111111 11111111 11111100

		System.out.println(-1 << 3);
		// -8
		// 11111111 11111111 11111111 11111000
		
		System.out.println(-1 << 30);
		// -1073741824
		// 11000000 00000000 00000000 00000000
		
		System.out.println(-1 << 31);
		// -2147483648
		// 10000000 00000000 00000000 00000000
		
		System.out.println(-1 << 32);
		// -1
		// 11111111 11111111 11111111 11111111
				
	}
}
