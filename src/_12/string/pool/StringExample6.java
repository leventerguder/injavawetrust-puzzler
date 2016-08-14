package _12.string.pool;

public class StringExample6 {

	public static void main(String[] args) {
		
		String lang1 = new String("java8");
		String lang2 = new String("java8");

		System.out.println(lang1 == lang2); //false
		System.out.println(lang1.equals(lang2)); //true
	}
}
