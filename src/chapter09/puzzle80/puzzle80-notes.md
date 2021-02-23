# Puzzle 80: Further Reflection

What does the program print?

<pre>
public class Outer {

    public static void main(String[] args) throws Exception {
        new Outer().greetWorld();
    }

    private void greetWorld() throws Exception {
        System.out.println(Inner.class.newInstance());
    }

    public class Inner {
        public String toString() {
            return "Hello world";
        }
    }
}

</pre>

If you try running it, you'll find the actual output to be longer and more confusing:

<pre>
Exception in thread "main" InstantiationException: Outer$Inner
</pre>

Class.newInstance says that it throws InstantiationException if the Class object "represents an abstract class, 
an interface, an array class, a primitive type, or void; or if the class has no nullary [in other words, parameterless] 
constructor; or if the instantiation fails for some other reason" [Java-API]. 
Which of these conditions apply? Unfortunately, the exception message fails to provide even a hint.


Only the last two of these reasons could possibly apply: Either Outer.Inner has no nullary constructor or 
the instantiation failed "for some other reason." When a class has no explicit constructor, 
as is the case for Outer.Inner, Java automatically provides a default public constructor that takes no parameters 
[JLS 8.8.9], so there should be a nullary constructor. Nevertheless, the newInstance invocation fails because 
Outer.Inner has no nullary constructor!

The constructor of a non-static nested class is compiled such that it has as its first parameter 
an additional implicit parameter representing the immediately enclosing instance [JLS 13.1]. 
This parameter is passed implicitly when you invoke the constructor from any point in the code 
where the compiler can find an appropriate enclosing instance. But this applies only when you 
invoke the constructor normally: nonreflectively. When you invoke the constructor reflectively, 
this implicit parameter must be passed explicitly, which is impossible with Class.newInstance. 
The only way to pass this implicit parameter is to use java.lang.reflect.Constructor. 
When this change is made to the program, it prints Hello world as expected:

<pre>
private void greetWorld() throws Exception {
    Constructor c = Inner.class.getConstructor(Outer.class);
    System.out.println(c.newInstance(Outer.this));
}
</pre>

Alternatively, you might observe that Inner instances have no need for an enclosing Outer instance and 
so declare the class Inner to be static. Unless you have a compelling need for an enclosing instance, 
prefer static member classes over nonstatic [EJ Item 18]. This simple change will fix the program:

<pre>
public static class Inner { ... }
</pre>

The reflective model of Java programs is not the same as the language model. 
Reflection operates at the level of the virtual machine, exposing many details of the translation of 
Java programs into class files. Some of these details are mandated by the language specification, 
but others differ from implementation to implementation. The mapping from Java programs into class files was 
straightforward in early versions of the language, but it became more complex with the addition of advanced 
language features that are not directly supported in the VM, 
such as nested classes, covariant return types, generics, and enums.


Because of the complexity of the mapping from Java programs to class files, avoid using reflection to instantiate inner classes. 
More generally, be aware that when using reflection on program elements defined with advanced language features, 
the reflective view of the program may differ from the source view. 
Avoid depending on details of the translation that are not mandated by the language specification. 
The lesson for platform implementers is, once again, to provide clear and precise diagnostics.
