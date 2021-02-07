package chapter04.puzzle29;

public class NaN {

	public static void main(String[] args) {

		double number = 0.0 / 0.0;

		System.out.println(number); // NaN

		System.out.println(number - number); // NaN

		System.out.println(number == number); // false
	}
}
