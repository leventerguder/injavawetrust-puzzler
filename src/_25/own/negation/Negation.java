package _25.own.negation;

public class Negation {

	public static void main(String[] args) {

		System.out.println(Double.NaN == Double.NaN); // false
		System.out.println(Double.NaN == -Double.NaN); // false
		System.out.println(Double.POSITIVE_INFINITY == -Double.POSITIVE_INFINITY); // false

		int i = Integer.MIN_VALUE;

		// 10000000 00000000 00000000 00000000 --> (-2147483648) Integer.MIN_VALUE
		
		// negate Integer.MIN_VALUE
		
		//		
		//		01111111 11111111 11111111 11111111
		//		+				                  1
		//		-----------------------------------								  
		//		10000000 00000000 00000000 00000000 (-2147483648)
		
		// so Integer.MIN_VALUE == - Integer.MIN_VALUE;

		while (i != 0 && i == -i) {
			System.out.println("works or not ?");
		}

	}
}
