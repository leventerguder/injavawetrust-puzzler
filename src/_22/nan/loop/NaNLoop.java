package _22.nan.loop;

public class NaNLoop {

	public static void main(String[] args) {
		double number = Double.NaN;
		while (number != number) {
			System.out.println("works or not?");
		}
	}
}
