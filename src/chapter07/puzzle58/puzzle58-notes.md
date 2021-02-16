# Puzzle 58: Making a Hash of It


This puzzle attempts to learn from the mistakes of the previous one. 
Again the program consists of a Name class and a main method that puts a name into a hash set and 
checks whether the set contains the name. This time, however, the Name class does override the hashCode method. 
What does this program print?

<pre>

import java.util.*;

public class Name {
    private final String first, last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Name n) {
        return n.first.equals(first) && n.last.equals(last);
    }

    public int hashCode() {
        return 31 * first.hashCode() + last.hashCode();
    }

    public static void main(String[] args) {
        Set<Name> s = new HashSet<Name>();
        s.add(new Name("Donald", "Duck"));
        System.out.println(

                s.contains(new Name("Donald", "Duck")));
    }
}

</pre>



The flaw in this program is similar to the one in Puzzle 57. 
In that puzzle, Name overrides the equals method but fails to override hashCode; 
in this puzzle, Name overrides the hashCode method but fails to override equals.
That is not to say that Name doesn't declare an equals method; it does, but it's the wrong one. 
The Name class declares an equals method whose argument is of type Name rather than Object. 
The author of this class probably intended to override the equals method but mistakenly overloaded it 



Fixing the program is as simple as replacing the overloaded equals method with the overriding one found in Puzzle 57. 
With this equals method, the program prints true as expected:

<pre>
public boolean equals(Object o) {
    if (!(o instanceof Name))
        return false;
    Name n = (Name)o;
    return n.first.equals(first) && n.last.equals(last);
}

</pre>

To make the program work, you merely have to add the overriding equals method. 
You don't have to eliminate the overloaded one, but you are better off without it. 



The lesson of this puzzle is: Don't overload a method when you want to override. 
To avoid unintentional overloading, mechanically copy the declaration of each superclass method 
that you want to override, or better yet, let your IDE do it for you. 

apply the @Override annotation to each method declaration that is intended to override a superclass method

<pre>

@Override public boolean equals(Object o) { ... }
</pre>
