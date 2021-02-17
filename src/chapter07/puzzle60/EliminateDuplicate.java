package chapter07.puzzle60;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class EliminateDuplicate {

    // puzzle - A
    static <E> List<E> withoutDuplicates(List<E> original) {
        return new ArrayList<>(new LinkedHashSet<E>(original));
    }

    public static void main(String[] args) {

        List<String> duplicates = List.of("spam", "sausage", "spam", "sausage", "spam", "sausage");
        System.out.println(withoutDuplicates(duplicates));
    }
}
