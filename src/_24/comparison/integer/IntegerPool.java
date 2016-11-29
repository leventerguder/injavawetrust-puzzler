package _24.comparison.integer;

public class IntegerPool {

	public static void main(String[] args) {
		Integer number1 = 100;
		Integer number2 = 100;

		System.out.println("number1 == number2 : " + (number1 == number2));
		//true
		Integer number3 = 1000;
		Integer number4 = 1000;

		System.out.println("number3 == number4 : " + (number3 == number4));
		//false
	}
}
