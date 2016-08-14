package _07.swap;

public class SwatWithMathOperation {

	public static void main(String[] args) {
		int x = 13;
		int y = 7;

		System.out.println("x:" + x + " y:" + y);

		x = x + y;
		y = x - y;
		x = x - y;

		System.out.println("x:" + x + " y:" + y);
	}
}
