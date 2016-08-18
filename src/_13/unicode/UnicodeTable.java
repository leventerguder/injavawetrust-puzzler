package _13.unicode;

public class UnicodeTable {

	public static void main(String[] args) {
		// \u0022 double quote (")
		// http://unicode-table.com/en/#0022
		System.out.println("a\u0022.length() + \u0022b".length());
		System.out.println("a".length() + "b".length());

		System.out.println("a\u0021.length() + \u0021b".length());
		System.out.println("a!.length() + !b".length());
		
		System.out.println("\u0024".equals("$"));		

	}
}
