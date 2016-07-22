package _01.oddity;

public class OddityTest {

	// not works for negative numbers!

	// public static boolean isOdd(int number) {
	// return number % 2 == 1;
	// }
	//

	// It is ok for negative numbers too.
	
	// public static boolean isOdd(int number) {
	// return number % 2 != 0;
	// }
	
	//better performance
	public static boolean isOdd(int number) {
		return (number & 1) != 0;
	}

	public static void main(String[] args) {
		System.out.println("IsOdd:" + isOdd(5));
		System.out.println("IsOdd:" + isOdd(10));
		System.out.println("IsOdd:" + isOdd(-5));
		System.out.println("IsOdd:" + isOdd(-10));
	}
}