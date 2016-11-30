package _24.comparison.integer;

public class IntegerWithNew {

	public static void main(String[] args) {
		
		Integer number1 = new Integer(100);
		Integer number2 = new Integer(100);
		

		System.out.println("number1 == number2 : " + (number1 == number2));
		
		Integer number3 = new Integer(1000);
		Integer number4 = new Integer(1000);
		
		System.out.println("number3 == number4 : " + (number3 == number4));
		
	}
}
