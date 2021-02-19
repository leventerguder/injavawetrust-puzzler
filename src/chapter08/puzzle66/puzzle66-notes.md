# Puzzle 66: A Private Matter

In this program, a subclass field has the same name as a superclass field. What does the program print?

<pre>
class Base {
    public String className = "Base";
}

class Derived extends Base {
    private String className = "Derived";
}

public class PrivateMatter {

    public static void main(String[] args) {
        System.out.println(new Derived().className);
    }
}
</pre>


If you tried compiling the program, you found that neither analysis is correct. 
The program doesn't compile, but the error is in the class PrivateMatter.

className is a field, Derived.className hides Base.className rather than overriding it

Although Base has a public field className, this field is not inherited into 
Derived because it is hidden by Derived.className.


Note that it is possible to access the public field Base.className in a Derived instance even though it is hidden, 
by casting the Derived instance to Base.

<pre>
public static void main(String[] args) {
        System.out.println(((Base)new Derived()).className);
    }

</pre>


This demonstrates a big difference between overriding and hiding. Once a method is overridden in a subclass, 
you can't invoke it on an instance of the subclass (except from within the subclass, by using the super keyword). 
You can, however, access a hidden field by casting the subclass instance to a superclass in which the field is not hidden.


If you want the program to print Derived that is, you want it to exhibit overriding behavior use public methods 
in place of public fields. This is, in any case, a good idea because it provides better encapsulation [EJ Item 19].

<pre>
class Base {
    public String getClassName() {
        return "Base";
    }
}

class Derived extends Base {

    public String getClassName() {
        return "Derived";
    }
}


public class PublicMatter {
    public static void main(String[] args) {
        System.out.println(new Derived().getClassName());
    }
}

</pre>

The lesson of this puzzle is that hiding is generally a bad idea. The language allows you to hide variables, 
nested types, and even static methods (as in Puzzle 48), but just because you can doesn't mean that you should.
To avoid this confusion, simply avoid hiding.


A class that hides a field with one whose accessibility is more restrictive than that of the hidden field, 
as in our original program, violates the principle of subsumption, 
also known as the Liskov Substitution Principle [Liskov87]. 


In summary, hiding occurs when you declare a field, a static method, or a nested type 
whose name is identical to an accessible field, method, or type, respectively, in a superclass. 
Hiding is confusing; avoid it. Hiding fields in a manner that violates subsumption is especially harmful. 
More generally, avoid name reuse other than overriding.
