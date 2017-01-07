package _27.precedence;

public class ClockPrecedenceParentheses {

	public static void main(String[] args) {
		int minutes = 0;
		for (int ms = 0; ms < 60 * 60 * 1000; ms++)
			if (ms % (60 * 1000) == 0) //bugfixed
				minutes++;
		System.out.println(minutes);
	}
}
