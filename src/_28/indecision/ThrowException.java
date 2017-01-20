package _28.indecision;

public class ThrowException {
	public static void main(String[] args) {
		System.out.println(decision());
	}

	@SuppressWarnings("finally")
	static boolean decision() {
		try {
			throw new Exception();
		} finally {
			return false;
		}
	}
}
