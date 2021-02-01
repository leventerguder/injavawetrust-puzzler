package chapter02.puzzler07;

public class SwatWithMathOperation {

	public static void main(String[] args) {

		// https://www.geeksforgeeks.org/swap-two-numbers-without-using-temporary-variable/
		int x = 13;
		int y = 7;

		x = x + y;
		y = x - y;
		x = x - y;

		System.out.println("After Swap");
		System.out.println("x : " + x);
		System.out.println("y : " + y);

	}
}
