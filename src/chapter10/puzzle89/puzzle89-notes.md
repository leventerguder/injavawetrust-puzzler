# Puzzle 89: Generic Drugs

Like the previous puzzle, this one makes heavy use of generics. Learning from our previous mistakes, 
we refrain from using raw types. This program implements a simple linked list data structure. 
The main program builds a list with two elements and dumps its contents. What does the program print?


<pre>
public class LinkedList<E> {

    private Node<E> head = null;

    private class Node<E> {
        E value;
        Node<E> next;

        // Node constructor links the node as a new head
        Node(E value) {
            this.value = value;
            this.next = head;
            head = this;
        }
    }

    public void add(E e) {
        new Node<E>(e);
        // Link node as new head
    }

    public void dump() {
        for (Node<E> n = head; n != null; n = n.next)
            System.out.print(n.value + " ");
    }

    public static void main(String[] args) {

        LinkedList<String> list = new LinkedList<String>();
        list.add("world");
        list.add("Hello");
        list.dump();
    }


}
</pre>


Again, this program appears reasonably straightforward. 
New elements are added to the head of the list and the dump method prints the list starting with the head. 
Therefore, elements are printed in the opposite order they are added. 
In this case, the program first adds "world" and then "Hello", so it looks as if it is just a convoluted Hello world program. 
Sadly, if you tried to compile it, you found that it doesn't compile. 
The error messages from the compiler are downright baffling:

<pre>
LinkedList.java:11: incompatible types
found : LinkedList<E>.Node<E>
required: LinkedList<E>.Node<E>
            this.next = head;
                        ^
LinkedList.java:12: incompatible types
found : LinkedList<E>.Node<E>
required: LinkedList<E>.Node<E>
head = this; 
</pre>


It appears that the compiler is complaining that a type isn't compatible with itself! Appearances, as usual, are deceiving. 
The "found" and "required" types are unrelated to each other. They appear identical because the program uses the same 
name to refer to different types. Specifically, the program contains two different declarations for type parameters named E. 
The first is the type parameter for LinkedList, and the second is the type parameter for the inner class LinkedList.Node. 
The latter shadows the former within the inner class. The lesson that we learned in Puzzles 71, 73, and 79 applies 
here as well: Avoid shadowing type parameter names.

There is no way to refer to a type parameter except by its simple name, 
so the error message has no way to tell you that these two uses of the name E refer to different types. 
The error message would be clearer if we systematically renamed the type parameter for Node from E to, say, T. 
It wouldn't fix the problem, but it would shed some light on it. This approach yields the following error messages:

What the compiler is trying to tell us is that the program is way too complicated. 
An inner class of a generic class has access to the type parameters of its outer class. 
It was the clear intent of the program's author that the type parameter for a Node would always be the same as for the 
enclosing LinkedList, so there is no reason for Node to have a type parameter of its own. 
To fix the program, simply eliminate the type parameter in the inner class:

<pre>
// Fixed but could be MUCH better
public class LinkedListFixed<E> {

    private Node head = null;

    private class Node {
        E value;
        Node next;

        // Node constructor links the node as a new head
        Node(E value) {
            this.value = value;
            this.next = head;
            head = this;
        }
    }

    public void add(E e) {
        new Node(e);
        // Link node as new head
    }

    public void dump() {
        for (Node n = head; n != null; n = n.next)
            System.out.print(n.value + " ");
    }
}

</pre>


A better fix, then, is to modify the original program to move the manipulation of head into LinkedList.add, 
making Node a static nested class rather than a true inner class. 
Static nested classes do not have access to the type parameters of enclosing classes, so now Node really 
does need a type parameter of its own. The resulting program is simple, clear, and correct:


<pre>
public class LinkedListFixed<E> {

    private Node head = null;

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public void add(E e) {
        head = new Node<E>(e, head);
    }

    public void dump() {
        for (Node<E> n = head; n != null; n = n.next)
            System.out.print(n.value + " ");
    }

    public static void main(String[] args) {

        LinkedListFixed<String> list = new LinkedListFixed<String>();
        list.add("world");
        list.add("Hello");
        list.dump();
    }
}

</pre>

In summary, inner classes of generic classes have access to the enclosing class's type parameters, which can be confusing. 
The misunderstanding illustrated in this puzzle is common among programmers first learning generics. 
It isn't necessarily wrong to have an inner class in a generic class, but the need for this is rare, 
and you should consider refactoring your code to avoid it. When you have one generic class nested inside another, 
give their type parameters different names, even if the nested class is static. For language designers, 
perhaps it makes sense to forbid shadowing of type parameters, in the same way that 
shadowing of local variables is forbidden. Such a rule would have caught the bug in this puzzle.
