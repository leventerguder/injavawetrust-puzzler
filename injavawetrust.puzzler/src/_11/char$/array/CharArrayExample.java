package _11.char$.array;

public class CharArrayExample {
	public static void main(String[] args) {

		char[] numbers = { '1', '2', '3' };
		System.out.println(numbers);
		
		//public void println(String x) 
		System.out.println("Java " + numbers);

		System.out.println("fix : ");
		
		System.out.println("Java " + String.valueOf(numbers));
		
 
		System.out.print("Java ");
		System.out.println(numbers);
		
		// public void println(Object x) 
		Object numbers2 = new char[]{ '1', '2', '3' };
		System.out.println(numbers2);
	}
}
