package chapter07.puzzle57;

import java.util.HashSet;
import java.util.Objects;
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

    /*
    always override hashCode when you override equals.
    More generally, obey the general contract when you override a method that has one.
     */
    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }

    /*
    public int hashCode() {
        return 37 * first.hashCode() + last.hashCode();
    }
     */

    public static void main(String[] args) {
        Set<NameFixed> s = new HashSet<>();
        s.add(new NameFixed("Mickey", "Mouse"));
        System.out.println(s.contains(new NameFixed("Mickey", "Mouse")));
    }
}
