package chapter03.puzzle14;

public class EscapeRout {
    public static void main(String[] args) {

        /*
        A naive analysis of the program suggests that it should print 26 because there are 26 characters between
        the quotation marks that bound the string "a\u0022.length() + \u0022b". A deeper analysis suggests that
        the program should print 16, as each of the two Unicode escapes requires six characters
        n the source file but represents only one character in the string.
        The string is therefore ten characters shorter than it appears.
        Running the program tells a different story. It prints neither 26 nor 16 but 2.
         */

        // https://www.branah.com/unicode-converter
        // \u0022 is the Unicode escape for double quote (")
        System.out.println("a\u0022.length() + \u0022b".length());

        System.out.println("a".length() + "b".length());

    }
}
