package _27.precedence;

public class Clock {

	public static void main(String[] args) {
		int minutes = 0;
		// Spacing can be deceptive, but parentheses never lie
		for (int ms = 0; ms < 60 * 60 * 1000; ms++)
			if (ms % 60 * 1000 == 0)
				minutes++;
		System.out.println(minutes);
	}

}

// expression is equivalent to (ms % 60000 == 0), but it isn't.
// The remainder and multiplication operators have the same precedence [JLS 15.17], 
// so the  expression ms % 60*1000 is equivalent to (ms % 60) * 1000. This expression is
// equal to 0 if (ms % 60) is 0, so the loop increments minutes every 60
// iterations. This accounts for the final result being off by a factor of a
// thousand.
