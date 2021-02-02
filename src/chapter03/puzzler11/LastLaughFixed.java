package chapter03.puzzler11;

public class LastLaughFixed {

    public static void main(String[] args) {

        /*
        This works, but it's ugly.
         */
        System.out.println("" + 'H' + 'a');

        /*
        This idiom ensures that subexpressions are converted to strings.
        Although useful it is a bit ungainly and can lead to some confusion itself.
         */
        System.out.println("2 + 2 = " + 2 + 2);

        /*
         you also have the option of using the printf facility:
         */
        System.out.printf("%c%c", 'H', 'a');

        /*
        You could use the libraries. For example, you could use a StringBuffer :
         */
        StringBuffer sb = new StringBuffer();
        sb.append('H');
        sb.append('a');
        System.out.println(sb);

    }
}
