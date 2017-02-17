package _29.catch$;

public class CatchException {
	public static void main(String[] args) {
		try {
			System.out.println("Hello CatchException#main!");
		} catch (Exception e) {
			System.out.println("I have never seen println fail!");
		}
	}
}