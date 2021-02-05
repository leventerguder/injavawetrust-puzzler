package chapter03.puzzle21;

import java.io.File;

public class MeToo {

    public static void main(String[] args) {

        // Unix/Linux /
        // Windows \
        // Windows system throws Exception!
        // \ is escape char , it is invalid!

        System.out.println(MeToo.class.getName().replaceAll("\\.", File.separator) + ".class");

        /*
         If, however, the file separator is a backslash, as it is on Windows, the program prints something like this:
        Exception in thread "main"
        StringIndexOutOfBoundsException: String index out of range: 1
        at java.lang.String.charAt(String.java:558)
        ....
         */
    }

}
