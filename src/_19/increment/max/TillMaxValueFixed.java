package _19.increment.max;

public class TillMaxValueFixed {
	public static final int END = Integer.MAX_VALUE;
	public static final int START = END - 100;

	public static void main(String[] args) {
		int count = 0;

		//use long i intead of int i
		for (long i = START; i <= END; i++)
			count++;
		System.out.println(count);

	}
	
	 
}
