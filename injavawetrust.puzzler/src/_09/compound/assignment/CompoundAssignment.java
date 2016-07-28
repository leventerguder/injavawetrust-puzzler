package _09.compound.assignment;

public class CompoundAssignment {
	public static void main(String[] args) {
		int i = 123456;
		short x = 0;
		x += i;
		// x=x+i; // "possible loss of precision"
		System.out.println(x);// -7616

		int j = 1000;
		byte b = 0;
		b += j;
		System.out.println(b); // -24

		float f = 0;
		double d = 11.123456789;
		f += d;
		System.out.println(f); // 11.123457
	}
}
