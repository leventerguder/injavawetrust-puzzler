package _12.string.pool;

public class StringExample3 {

	public static void main(String[] args) {

		String lang1 = "java8";

		final String version = "8";
		String lang2 = "java" + version; 
		//Constant Expression , version is final

		System.out.println(lang1 == lang2);
	}
}
