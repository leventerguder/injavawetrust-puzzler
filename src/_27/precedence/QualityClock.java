package _27.precedence;

public class QualityClock {

	// better way to fix,
	private static final int MS_PER_HOUR = 60 * 60 * 1000;
	private static final int MS_PER_MINUTE = 60 * 1000;

	public static void main(String[] args) {
		int minutes = 0;
		for (int ms = 0; ms < MS_PER_HOUR; ms++)
			if (ms % MS_PER_MINUTE == 0)
				minutes++;
		System.out.println(minutes);
	}
}
