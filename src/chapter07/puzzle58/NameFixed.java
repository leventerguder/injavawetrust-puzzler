package chapter07.puzzle58;


import java.util.HashSet;
import java.util.Set;

public class NameFixed {
    private final String first, last;

    public NameFixed(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object o) {
        if (!(o instanceof NameFixed))
            return false;
        NameFixed n = (NameFixed) o;
        return n.first.equals(first) && n.last.equals(last);
    }

    public int hashCode() {
        return 31 * first.hashCode() + last.hashCode();
    }

    public static void main(String[] args) {

        Set<NameFixed> s = new HashSet<NameFixed>();
        s.add(new NameFixed("Donald", "Duck"));

        System.out.println(s.contains(new NameFixed("Donald", "Duck"))); // trues
    }
}
