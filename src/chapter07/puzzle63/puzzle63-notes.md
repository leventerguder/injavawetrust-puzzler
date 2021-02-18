# Puzzle 63: More of the Same

This program is similar to the previous one, except this one is object-oriented. 
Learning from our previous mistake, this version uses a general-purpose Map implementation, 
a HashMap, in place of the previous program's IdentityHashMap. What does this program print?

<pre>
import java.util.*;

public class MoreNames {

    private Map<String, String> m = new HashMap<String, String>();

    public void MoreNames() {
        m.put("Mickey", "Mouse");
        m.put("Mickey", "Mantle");
    }

    public int size() {
        return m.size();
    }

    public static void main(String args[]) {
        MoreNames moreNames = new MoreNames();
        System.out.println(moreNames.size());
    }
}

</pre>

This program looks straightforward. The main method creates a MoreNames instance by 
invoking the parameterless constructor. The MoreNames instance contains a private Map field (m), 
which is initialized to an empty HashMap. The parameterless constructor appears to put two mappings into the map m, 
both with the same key (Mickey). As we know from the previous puzzle, the ballplayer (Mickey Mantle) should overwrite 
the rodent (Mickey Mouse), leaving a single mapping. The main method then invokes the size method on the MoreNames 
instance, which in turn invokes size on the map m and returns the result, presumably 1. There's only one problem 
with this analysis: The program prints 0, not 1. What's wrong with the analysis?


The problem is that MoreNames has no programmer-declared constructor. 
What it does have is a void-returning instance method called MoreNames, 
which the author probably intended as a constructor.


Fixing the program is as simple as removing the void return type from the Names declaration, 
which turns it from an instance method declaration into a constructor declaration. 
With this change, the program prints 1 as expected.


The lesson of this puzzle is: Don't accidentally turn a constructor declaration into a method 
declaration by adding a return type. Although it is legal for a method to have the same name as 
the class in which it's declared, you should never do this. More generally, obey the standard 
naming conventions, which dictate that method names begin with lowercase letters, 
whereas class names begin with uppercase letters.
