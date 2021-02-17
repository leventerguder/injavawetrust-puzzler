package chapter07.puzzle60;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArraysDeepToString {

    public static void main(String[] args) {

        // puzzle C
        // Arrays.deepToString
        String[] array = {"hello", "java"};
        String deepToString = Arrays.deepToString(array);

        System.out.println(deepToString);

        String[][] matrix = {{"a", "b"}, {"c", "d"}};
        String deepToString2 = Arrays.deepToString(matrix);

        System.out.println(deepToString2);
    }
}
