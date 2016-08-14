package _12.string.pool;

public class StringExample4 {

	public static void main(String[] args) {
		
		String lang1 = "java8";

		String version = "8";
		String lang2 = "java" + version; 
		//Not Constant Expression

		System.out.println(lang1 == lang2);
		
		System.out.println(lang1.intern() == lang2.intern()); //true
		System.out.println(lang1.equals(lang2)); //true
	}
}