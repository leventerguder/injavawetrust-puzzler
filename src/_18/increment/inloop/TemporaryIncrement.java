package _18.increment.inloop;

public class TemporaryIncrement {
	public static void main(String[] args) {
		int j = 0;
		for (int i = 0; i < 100; i++) {
			//j = j++; equivalent  ;
			
			int temp = j;
			j = j + 1;
			j = temp;
		}

		System.out.println(j);
	}
}
