package _28.indecision;

public class Exit {
	public static void main(String[] args) {
		System.out.println(decision());
	}

	@SuppressWarnings("finally")
	static boolean decision() {
		try {
			System.exit(0);
			System.out.println("After exit!");
		} finally {
			return false;
		}
	}
}
