# Puzzle 62: The Name Game

This program puts two mappings into a map and prints its size. What does it print?

<pre>
import java.util.*;


public class NameGame {
    public static void main(String args[]) {

        Map<String, String> m =
                new IdentityHashMap<String, String>();
        m.put("Mickey", "Mouse");
        m.put("Mickey", "Mantle");
        System.out.println(m.size());

    }
}

</pre>


A naive analysis of this program suggests that it should print 1. The program puts two mappings into the map, 
but both have the same key (Mickey). It's a map, not a multimap, so the baseball legend (Mickey Mantle) should 
overwrite the animated rodent (Mickey Mouse), leaving a single mapping in the map.


The documentation for IdentityHashMap says, "this class implements the Map interface with a hash table, 
using reference-equality in place of [value]-equality when comparing keys" [Java-API]. In other words, 
the program will print 2 rather than 1 if the second occurrence of the string literal "Mickey" 
evaluates to a different String instance from the first.

So does the program print 1, does it print 2, or might its behavior vary from implementation to implementation?


The important lesson of this puzzle is: Don't use IdentityHashMap unless you need its identity-based semantics; 
it is not a general-purpose Map implementation. These semantics are useful for implementing topology-preserving 
object graph transformations, such as serialization or deep-copying.
