package _08.ternary;

public class TernaryExample {

	public static void main(String[] args) {
		int point = 75;
		String result = point < 50 ? "Fail" : "Success";
		System.out.println(result);

		String result2 = point < 50 ? "F" : point > 90 ? "A" : "B";
		System.out.println(result2);
	}
}
