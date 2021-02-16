# Puzzle 57: What's in a Name?


This program consists of a simple immutable class that represents a name, with a main method that 
puts a name into a set and checks whether the set contains the name. What does the program print?

<pre>
import java.util.HashSet;
import java.util.Set;

public class Name {

    private final String first, last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Name))
            return false;
        Name n = (Name) o;
        return n.first.equals(first) && n.last.equals(last);
    }

    public static void main(String[] args) {
        Set<Name> s = new HashSet<>();
        s.add(new Name("Mickey", "Mouse"));
        System.out.println(s.contains(new Name("Mickey", "Mouse")));
    }
}

</pre>


A Name instance consists of a first name and a last name. Two Name instances are equal, as computed by the equals method, 
if their first names are equal and their last names are equal. First names and last names are compared using the equals
method defined in String. Two strings are equal if they consist of the same characters in the same order. 
Therefore, two Name instances are equal if they represent the same name. For example, the following method invocation returns true:

<pre>
new Name("Mickey", "Mouse").equals(new Name("Mickey", "Mouse"))
</pre>

To fix the problem, simply add an appropriate hashCode method to the Name class. 
Although any method whose return value is determined solely by the first and last name will satisfy the contract, 
a high-quality hash function should attempt to return different hash values for different names. 
The following method will do nicely [EJ Item 8].

<pre>
public int hashCode() {
    return 37 * first.hashCode() + last.hashCode();
}

</pre>


In summary, always override hashCode when you override equals. 
More generally, obey the general contract when you override a method that has one.
