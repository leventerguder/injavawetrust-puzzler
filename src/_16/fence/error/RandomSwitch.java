package _16.fence.error;

import java.util.Random;

public class RandomSwitch {
	private static Random rnd = new Random();

	public static void main(String[] args) {
		StringBuffer word = null;
		switch (rnd.nextInt(2)) {
		case 1:
			word = new StringBuffer('P');
		case 2:
			word = new StringBuffer('G');
		default:
			word = new StringBuffer('M');
		}

		// problem 1 - nextInt(2) can produce 0 or 1 , the parameter value is exclusive
		// problem 2 - there is no break in case.
		// problem 3 - there is no StringBuffer(char) constructor !
		// it calls public StringBuffer(int capacity)
		
		word.append('a');
		word.append('i');
		word.append('n');
		System.out.println(word);
	}
}
