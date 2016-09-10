package _17.byte$.inloop;

public class Bounds {

	public static void main(String[] args) {
		for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
			if (b == 0x90)
				// 0x90 is hexadecimal number.
				// in decimal it is 144. 144 is out of byte's bound.
				System.out.print("if!");
		}

		System.out.println(Byte.MIN_VALUE); //-128
		System.out.println(Byte.MAX_VALUE); //127
	}
}
