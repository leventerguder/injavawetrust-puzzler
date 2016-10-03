package _21.infinite.loop;

public class DoubleInfinityLoop {
	public static void main(String[] args) {

		double number = Double.POSITIVE_INFINITY;
		while (number == number + 1) {
			System.out.println("works or not ?");
		}
	}

}
