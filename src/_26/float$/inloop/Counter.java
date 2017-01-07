package _26.float$.inloop;

public class Counter {

	public static void main(String[] args) {

		final int START = 2000000000;
		// 2147483647 Integer.MAX_VALUE

		int counter = 0;
		for (float f = START; f < START + 50; f++)
			counter++;
		System.out.println(counter);
	}

}
