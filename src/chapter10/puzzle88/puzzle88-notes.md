# Puzzle 88: Raw Deal

This program consists of a single class representing a pair of like-typed objects. 
It makes heavy use of release 5.0 features, including generics, autoboxing, varargs, and the for-each loop.
What does it print?

<pre>
import java.util.*;

public class Pair<T> {

    private final T first;
    private final T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return first;
    }

    public T second() {
        return second;
    }

    public List<String> stringList() {
        return Arrays.asList(String.valueOf(first),
                String.valueOf(second));
    }

    public static void main(String[] args) {

        Pair p = new Pair<Object>(23, "skidoo");
        System.out.println(p.first() + " " + p.second());
        for (String s : p.stringList())
            System.out.print(s + " ");
    }
}
</pre>

This program appears reasonably straightforward. It creates a pair whose first element is the 
Integer representing 23 and whose second element is the string "skidoo". 
Then the program prints the first and second elements of the pair, separated by a space. 
Finally, it iterates over the string representations of these elements and prints them again, 
so it ought to print 23 skidoo twice. Sadly, it doesn't even compile. Worse, 
the compiler's error message is terribly confusing:

<pre>
found: Object, required: String
        for (String s : p.stringList())
</pre>


This message would make sense if Pair.stringList were declared to return List<Object>, but it returns List<String>. 
What on earth is going on?

This rather surprising behavior is caused by the program's use of raw types.
A raw type is simply the name of a generic class or interface without any type parameters. 
For example, List<E> is a generic interface, List<String> is a parameterized type, and List is a raw type. 
In our program, the sole use of raw types is the declaration of the local variable p in main:

<pre>
Pair p = new Pair<Object>(23, "skidoo");
</pre>


A raw type is like its parameterized counterpart, but all its instance members are replaced by their erased counterparts. 
In particular, each parameterized type appearing in an instance method declaration is replaced with its raw counterpart [JLS 4.8]. 
The variable p in our program is of the raw type Pair, so its instance methods are erased. This includes the stringList method,
which is declared to return List<String>. The compiler interprets the program as if this method returned the raw type List.


While List<String> implements the parameterized type Iterable<String>, List implements the raw type Iterable.
Where Iterable<String> has an iterator method that returns the parameterized type Iterator<String>, 
Iterable has an iterator method that returns the raw type Iterator. 
Where the next method of Iterator<String> returns String, the next method of Iterator returns Object. 
Therefore, iterating over p.stringList() requires a loop variable of type Object, which explains the compiler's 
bizarre error message. The reason this behavior is so counterintuitive is that the parameterized type List<String>, 
which is the return type of the stringList method, has nothing to do with the type parameter of Pair, 
but it gets erased anyway.

You could attempt to fix the problem by changing the type of the loop variable fromStringtoObject:

<pre>
// Don't do this; it doesn't really fix the problem!
for (Object s : p.stringList())
    System.out.print(s + " ");
</pre>

This does cause the program to generate the expected output, but it doesn't really fix the problem. 
You lose all the benefits of generics, and the program wouldn't even compile if the loop invoked any String methods on s. 
The right way to fix the program is to provide a proper parameterized declaration for the local variable p:

<pre>
Pair<Object> p = new Pair<Object>(23, "skidoo");
</pre>

This underscores a key point: The raw type List is not the same as the parameterized type List<Object>. 
If the raw type is used, the compiler has no idea whether there are any restrictions on the type of elements permitted 
by the list, but it lets you insert elements of any type. This is not typesafe: If you insert an object of the wrong type, 
you may get a ClassCastException at any point in the future execution of the program. If the parameterized type 
List<Object> is used, the compiler knows that the list is allowed to contain elements of all types, 
so it is safe to let you insert any object

There is a third type that is closely related to these two: List<?> is a special kind of parameterized type known 
as a wildcard type. Like the raw type List, the compiler does not know what type of element is permitted, 
but because List<?> is a parameterized type, the language requires stronger type-checking. 
To avoid the possibility of a ClassCastException, the compiler won't let you 
insert any element except null into a list of type List<?>.

Raw types are a concession to existing code, which could not use generics prior to release 5.0. 
Many core library classes, such as collections, have been modified to take advantage of generics, 
but existing clients of those classes continue to behave as in previous releases. 
The behavior of raw types and their members was designed to mirror the pre-5 language, 
so as to retain compatibility.

The real problem with the Pair program is that the author did not decide what version of Java to use. 
Although most of the program uses generics, the variable p is declared with a raw type. 
To avoid bewildering compile-time errors, avoid writing raw types in code intended for release 5.0 or later. 
If an existing library method returns a raw type, store its result in a variable of an appropriate parameterized type. 
Better yet, upgrade to a version of the library that use generics, if possible. 
Although Java provides graceful interoperability between raw and parameterized types, 
limitations of raw types can interfere with the utility of generics.

This issue can arise in practice when reading Class annotations at run time with the getAnnotation method, 
which was added to class Class in release 5.0. Two Class objects are involved in each invocation of getAnnotation: 
the object on which the invocation is made and the object that is passed to indicate which annotation is desired. 
In a typical invocation, the former is obtained reflectively; the latter is a class literal, as in the following example:

<pre>
Author a = Class.forName(name).getAnnotation(Author.class);
</pre>

You do not have to cast the return value from getAnnotation to Author. Two things conspire to make this work: (1) 
The getAnnotation method is generic. It infers its return type from its parameter type. Specifically, 
it takes a parameter of type Class<T> and returns a value of type T. (2) Class literals provide generic type information. 
For example, the type of Author.class is Class<Author>. The class literal conveys both run-time and compile-time type information. 
Class literals used in this fashion are known as type tokens [Bracha04].

In contrast to class literals, Class objects obtained through reflection do not provide full generic type information: 
The return type of Class.forName is the wildcard type Class<?>. It is critical that you use this wildcard type rather 
than the raw type Class for the expression on which you invoke the getAnnotation method. If you use the raw type, 
the returned annotation will have the compile-time type of Annotation instead of the type indicated by the class literal. 
The following program fragment, which violates this advice, won't compile for the same reason that 
the original program in this puzzle did not:

<pre>
Class c = Class.forName(name);             // Raw type!
Author a = c.getAnnotation(Author.class);  // Type mismatch
</pre>

In summary, the members of a raw type are erased to simulate the behavior of the type before generics were added to the language. 
If you mix raw and parameterized types, you will not get the full benefit of generics, and you may get some very confusing compile-time errors. 
Also, a raw type is not the same as a parameterized type whose type parameter is Object. 
Finally, if you are migrating an existing code base to take advantage of generics, the best approach is to migrate one API at 
a time and to avoid entirely the use of raw types in new code.
