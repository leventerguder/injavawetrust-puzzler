package _31.catch$.constructor;

public class Reluctant {

	private Reluctant internalInstance = new Reluctant();

	public Reluctant() throws Exception {
		throw new Exception("I'm not coming out");
	}

	public static void main(String[] args) {
		try {
			Reluctant r = new Reluctant();
			System.out.println("Surprise!");
		} catch (Exception ex) {
			System.out.println("I told you so");
		}
	}
}