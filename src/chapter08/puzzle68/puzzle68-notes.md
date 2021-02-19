# Puzzle 68: Shades of Gray

Does the program print Black? Does it print White? Is it even legal?

<pre>
public class ShadesOfGray {

    public static void main(String[] args) {
        System.out.println(X.Y.Z);
    }
}


class X {
    static class Y {
        static String Z = "Black";
    }

    static C Y = new C();
}

class C {
    String Z = "White";
}

</pre>

it is legal and prints White. How could you possibly have known?

When a variable and a type have the same name and both are in scope, the variable name takes precedence

To avoid conflict between constant names and class names, treat acronyms as ordinary words in class names [EJ Item 38].

To avoid conflicts between variable names and package names, don't use a top-level package 
or domain name as a variable name.

Specifically, don't name a variable com, org, net, edu, java, or javax.

In summary, obey the standard naming conventions to avoid conflicts between different namespaces
you'll ensure that your programs never obscure class or package names.
