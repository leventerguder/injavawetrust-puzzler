# Puzzle 90: It's Absurd, It's a Pain, It's Superclass!

The following program doesn't actually do anything. Worse, it won't compile. Why not? How can you fix it?

<pre>
public class Outer {
    class Inner1 extends Outer {}
    class Inner2 extends Inner1 {}
}
</pre>

(This issue occurs in JDK6)

This program looks too simple to have anything wrong with it, but if you try to compile it, you get this helpful error message:

<pre>

Outer.java:3: cannot reference this before
              supertype constructor has been called
    class Inner2 extends Inner1 {}
</pre>

<pre>
public class Outer2 {
    class Inner1 extends Outer2 {
        public Inner1() {
            super();  // invokes Object() constructor
        }
    }

    class Inner2 extends Inner1 {
        public Inner2() {
            super();  // invokes Inner1() constructor
        }
    }
}

</pre>

Now the error message gives a bit more information:

<pre>
Outer.java:12: cannot reference this before
               supertype constructor has been called
    super(); // invokes Inner1() constructor
</pre>


Because the superclass of Inner2 is itself an inner class, an obscure language rule comes into play. 
As you know, the instantiation of an inner class, such as Inner1, requires an enclosing instance to be supplied to 
the constructor. Normally, it is supplied implicitly, but it can also be supplied explicitly with a 
superclass constructor invocation of the form expression.super(args) [JLS 8.8.7].

If the enclosing instance is supplied implicitly, the compiler generates the expression: It uses the this reference 
for the innermost enclosing class of which the superclass is a member. 
This is, admittedly, quite a mouthful, but it is what the compiler does. In this case, 
the superclass is Inner1. Because the current class, Inner2, extends Outer indirectly, 
it has Inner1 as an inherited member. Therefore, the qualifying expression for the superclass constructor is simply this.
The compiler supplies an enclosing instance, rewriting super to this.super. Had we done this ourselves, 
the compilation error would have made even more sense:

<pre>
Outer.java:12: cannot reference this before
               supertype constructor has been called
    this.super();
</pre>


Now the problem is clear: The default Inner2 constructor attempts to reference this before the superclass constructor 
has been called, which is illegal [JLS 8.8.7.1]. The brute-force way to fix this problem is to provide 
the reasonable enclosing instance explicitly:

<pre>
public class Outer {
    class Inner1 extends Outer { }
    class Inner2 extends Inner1 {
        public Inner2() {
            Outer.this.super();
        }
    } 
}

</pre>



This compiles, but it is mind-numbingly complex. 
There is a better solution: Whenever you write a member class, ask yourself, 
Does this class really need an enclosing instance? If the answer is no, make it static. 
Inner classes are sometimes useful, but they can easily introduce complications that make a program difficult to understand. 
They have complex interactions with generics (Puzzle 89), reflection (Puzzle 80), and inheritance (this puzzle). 
If you declare Inner1 to be static, the problem goes away. If you also declare Inner2 to be static, 
you can actually understand what the program does: a nice bonus indeed.


In summary, it is rarely appropriate for one class to be both an inner class and a subclass of another. 
More generally, it is rarely appropriate to extend an inner class; if you must, think long and hard about the enclosing instance.
Also, prefer static nested classes to nonstatic [EJ Item 18]. Most member classes can and should be declared static.
