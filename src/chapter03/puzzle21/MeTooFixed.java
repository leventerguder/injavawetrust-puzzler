package chapter03.puzzle21;

import java.io.File;
import java.util.regex.Matcher;

public class MeTooFixed {

    public static void main(String[] args) {


        System.out.println(MeToo.class.getName().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + ".class");

        System.out.println(File.separator);
        System.out.println(Matcher.quoteReplacement(File.separator));

        // String.replace(CharSequence, CharSequence)
        System.out.println(MeToo.class.getName().replace(".", File.separator) + ".class");

        // String.replace(char, char)
        System.out.println(MeToo.class.getName().replace('.', File.separatorChar) + ".class");
    }

}
