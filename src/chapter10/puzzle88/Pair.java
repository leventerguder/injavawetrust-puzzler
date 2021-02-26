package chapter10.puzzle88;

import java.util.*;

public class Pair<T> {

    private final T first;
    private final T second;

    public Pair(T first, T second) {
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

        Pair p = new Pair<Object>(23, "skidoo");
        System.out.println(p.first() + " " + p.second());
        //        for (String s : p.stringList())  // compile error
        //            System.out.print(s + " ");

        // Don't do this; it doesn't really fix the problem!
        for (Object s : p.stringList())
            System.out.print(s + " ");

    }
}
