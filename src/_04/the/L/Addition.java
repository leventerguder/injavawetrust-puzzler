package _04.the.L;

import java.util.ArrayList;
import java.util.List;

public class Addition {

	public static void main(String[] args) {

		// do not use "l" for long literals
		System.out.println(12345 + 5432l);

		// use the "L" for long literals
		System.out.println(12345 + 5432L);
		
		//do not use just "l" (el) character for identifiers  
		List<String> l = new ArrayList<String>();
		l.add("Java");
		
		System.out.println(1);
		System.out.println(l);
	}

}
