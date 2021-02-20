package chapter08.puzzle71;

import java.util.Arrays;

public class ImportDutyFixed {

    public static void main(String[] args) {
        printArgs(1, 2, 3, 4, 5);
    }

    static void printArgs(Object... args) {
        System.out.println(Arrays.toString(args));
    }
}
