package chapter02.puzzle04;

import java.util.ArrayList;
import java.util.List;

public class Elementary {

	public static void main(String[] args) {

		System.out.println(12345 + 5432l);


		/*
		Take a careful look at the two operands of the + operator. We are adding the int value 12345 to the long value 5432l.
		Note the subtle difference in shape between the digit 1 at the beginning of the left operand
		and the lowercase letter el at the end of the right operand

		Always use a capital el (L) in long literals, never a lowercase el (l).
		 */

		// use the "L" for long literals
		System.out.println(12345 + 5432L);
		
		//do not use just "l" (el) character for identifiers  
		List<String> l = new ArrayList<>();
		l.add("Java");
		
		System.out.println(1);
		System.out.println(l);
	}

}
