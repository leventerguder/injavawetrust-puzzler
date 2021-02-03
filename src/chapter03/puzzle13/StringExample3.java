package chapter03.puzzle13;

public class StringExample3 {

	public static void main(String[] args) {

		/*
		be aware that compile-time constants of type String are interned [JLS 15.28]. In other words,
		any two constant expressions of type String that designate
		the same character sequence are represented by identical object references.
		 */
		String lang1 = "java8";

		final String version = "8";
		String lang2 = "java" + version; 
		//Constant Expression , version is final

		System.out.println(lang1 == lang2);
	}
}
