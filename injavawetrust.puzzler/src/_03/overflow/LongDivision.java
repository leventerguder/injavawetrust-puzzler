package _03.overflow;

public class LongDivision {

	public static void main(String[] args) {

		// overflow length of int
		// it should be 864000000000
		// but the value is ; 500654080
		final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;

		final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;

		System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);

		System.out.println(24 * 60 * 60 * 1000);
		System.out.println(24 * 60 * 60 * 1000 * 1000);

	}
}
