package chapter03.puzzle20;

import java.util.regex.Pattern;

public class MeFixed {

    public static void main(String[] args) {

        System.out.println(Me.class.getName().replaceAll("\\.", "/") + ".class");

        System.out.println(Me.class.getName().replaceAll(Pattern.quote("."), "/") + ".class");

    }

}
