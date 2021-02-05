package chapter03.puzzle19;

public class Classifier {
    public static void main(String[] args) {
        System.out.println(classify('n') + classify('+') + classify('2'));
    }

    static String classify(char ch) {
        if ("0123456789".indexOf(ch) >= 0)
            return "NUMERAL ";
        if ("abcdefghijklmnopqrstuvwxyz".indexOf(ch) >= 0)
            return "LETTER ";

        // when there is no space between * and / we have a compile error here
        // ("+-*/&|!=".indexOf(ch) >= 0)

        /* (Operators not supported yet)
        if ("+-* /&|!=".indexOf(ch) >= 0)
                return "OPERATOR ";
         */

        return "UNKNOWN ";
    }

}
