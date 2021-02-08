package chapter04.puzzle32;

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

		/*
		Cache to support the object identity semantics of autoboxing for values between -128 and 127 (inclusive)
		 as required by JLS. The cache is initialized on first usage. The size of the cache may be controlled by
		 the -XX:AutoBoxCacheMax=<size> option. During VM initialization, java.lang.Integer.IntegerCache.high property
		 may be set and saved in the private system properties in the jdk.internal.misc.VM class.
		 */

		// check Integer#IntegerCache

		// We can change cache limit.
		// -Djava.lang.Integer.IntegerCache.high="1000"
	}
}
