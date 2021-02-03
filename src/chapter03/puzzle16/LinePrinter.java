package chapter03.puzzle16;

public class LinePrinter {
    public static void main(String[] args) {

        // Note: \ u000A is Unicode representation of linefeed (LF)
        /*
        if we remove space between \ and u , it is not valid.
        This program contains a single Unicode escape (\ u000A), located in its sole comment.
         */
        char c = 0x000A;
        System.out.println(c);

    }
}
