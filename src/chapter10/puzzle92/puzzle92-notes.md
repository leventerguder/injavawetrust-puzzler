# Puzzle 92: Twisted Pair

This program performs an unnatural act with an anonymous class. What does the program print?

<pre>
public class Twisted {

    private final String name;

    Twisted(String name) {
        this.name = name;
    }

    private String name() {
        return name;
    }

    private void reproduce() {
        new Twisted("reproduce") {
            void printName() {
                System.out.println(name());
            }
        }.printName();
    }

    public static void main(String[] args) {
        new Twisted("main").reproduce();
    }
}

</pre>

With that understanding, you might expect the program to print reproduce, because it invokes printName 
on the instance new Twisted("reproduce"), which passes the string "reproduce" to its superclass constructor 
to be stored in its name field. The printName method invokes the name method, which returns the contents of this field. 
But if you ran the program, you found that it prints main. Now why would it do a thing like that?



The intuition behind this behavior is that private members are never inherited [JLS 8.2]. 
In this case, the name method is not inherited into the anonymous class in reproduce. 
Therefore, the printName invocation in the anonymous class must refer to the method in the enclosing ("main") instance 
rather than the current ("reproduce") instance. This is the smallest enclosing scope that contains 
a method of the correct name (Puzzles 71 and 79).


This program violates the advice of Puzzle 90: The anonymous class inside "reproduce" is both an inner class of 
Twisted and extends it. This alone is sufficient to make the program unreadable. 
Throw in the complexity of invoking a private superclass method, and the program becomes pure gobbledygook. 
This puzzle serves to reinforce the lesson of Puzzle 6: If you can't tell what a program does by looking at it, 
it probably doesn't do what you want. Strive for clarity.
