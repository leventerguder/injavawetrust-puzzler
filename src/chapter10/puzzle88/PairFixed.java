package chapter10.puzzle88;

import java.util.Arrays;
import java.util.List;

public class PairFixed<T> {

    private final T first;
    private final T second;

    public PairFixed(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return first;
    }

    public T second() {
        return second;
    }

    public List<String> stringList() {
        return Arrays.asList(String.valueOf(first),
                String.valueOf(second));
    }

    public static void main(String[] args) {

        PairFixed<Object> p = new PairFixed<Object>(23, "skidoo");
        System.out.println(p.first() + " " + p.second());
        for (String s : p.stringList())
            System.out.print(s + " ");

        // This underscores a key point: The raw type List is not the same as the parameterized type List<Object>.


        /*
        There is a third type that is closely related to these two: List<?> is a special kind of parameterized type known
        as a wildcard type. Like the raw type List, the compiler does not know what type of element is permitted,
        but because List<?> is a parameterized type, the language requires stronger type-checking.
         */
        PairFixed<?> p2 = new PairFixed<Object>(23, "skidoo");
        System.out.println(p2.first() + " " + p2.second());
        for (String s2 : p2.stringList())
            System.out.print(s2 + " ");

    }
}
