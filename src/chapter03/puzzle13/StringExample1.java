package chapter03.puzzle13;

public class StringExample1 {

    public static void main(String[] args) {

		/*
		be aware that compile-time constants of type String are interned [JLS 15.28]. In other words,
		any two constant expressions of type String that designate
		the same character sequence are represented by identical object references.
		 */
        String lang1 = "java";
        String lang2 = "java";

        System.out.println(lang1 == lang2);

    }
}
