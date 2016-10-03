package _21.infinite.loop;

public class DoubleInfinity {

	public static void main(String[] args) {
		double i = 1.0 / 0;
		System.out.println(i); // Infinity

		double j = Double.POSITIVE_INFINITY;
		System.out.println(j);

		double k = Double.NEGATIVE_INFINITY;
		System.out.println(k);
	}
}
