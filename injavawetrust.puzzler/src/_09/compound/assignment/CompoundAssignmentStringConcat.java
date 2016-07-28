package _09.compound.assignment;

public class CompoundAssignmentStringConcat {

	public static void main(String[] args) {
		Object x = "injava";
		String i = "wetrust.com";

		x = x + i;

		Object y = "injava";
		String j = "wetrust.com";

		y += j;
		// The operator += is undefined for the argument type(s) Object, String
		// JDK 6
		// It is legal with JDK 7 , JDK8

		System.out.println(x);
		System.out.println(y);

		String z = "aaa";
		int number = 100;
		z += number;

		System.out.println(z);
	}
}
