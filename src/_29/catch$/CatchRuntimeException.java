package _29.catch$;

public class CatchRuntimeException {

	public static void main(String[] args) {
		try {
			System.out.println("Hello CatchRuntimeException!");
		} catch (RuntimeException e) {
			System.out.println("I have never seen println fail!");
		}
	}
}
