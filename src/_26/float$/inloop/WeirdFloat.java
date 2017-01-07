package _26.float$.inloop;

public class WeirdFloat {

	public static void main(String[] args) {

		float f1 = 2000000000f;
		float f2 = ++f1;
		float f3 = f1 + 1;
		float f4 = f1 + 64;

		System.out.println(f1 == f2);
		System.out.println(f2 == f3);
		System.out.println(f3 == f4);
		System.out.println(f1 == f4);

		System.out.println("f1: " + f1);
		System.out.println("f2: " + f2);
		System.out.println("f3: " + f3);
		System.out.println("f4: " + f4);

	}

}
