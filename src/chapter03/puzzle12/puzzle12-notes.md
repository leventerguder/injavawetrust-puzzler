# Puzzle 12: ABC
What does this program print?
<pre>
public class Abc {
    public static void main(String[] args) {
        String letters = "ABC";
        char[] numbers = { '1', '2', '3' };
        System.out.println(letters + " easy as " + numbers);
    } 
}
</pre>

One would hope that the program prints ABC easy as 123. Unfortunately, it does not. 
If you ran it, you found that it prints something like ABC easy as [C@16f0472. Why is the output so ugly?


Although char is an integral type, many libraries treat it specially,
because char values usually represent characters rather than integers. 
For example, passing a char value to println prints a Unicode character rather than its numerical code


So what is the behavior of invoking toString on a non-null char array? 
Arrays inherit the toString method from Object [JLS 10.7], whose specification says, 
"Returns a string consisting of the name of the class of which the object is an instance, 
the at-sign character '@', and the unsigned hexadecimal representation of the hash code of the object" [Java-API].

There are two ways to fix it. You can explicitly convert the array to a string before invoking string concatenation:

<pre>

System.out.println(letters + " easy as " + String.valueOf(numbers));
</pre>

Alternatively, you can break the System.out.println invocation in two to make use of the char[] overloading of println:
<pre>

System.out.print(letters + " easy as ");
System.out.println(numbers);

</pre>

Note that these fixes work only if you invoke the correct overloading of the valueOf or println method. 
In other words, they depend critically on the compile-time type of the array reference. 
The following program illustrates this dependency. It looks as though it incorporates the second fix described, 
but it produces the same ugly output as the original program because it invokes the Object overloading of println 
instead of the char[] overloading:

<pre>
// Broken - invokes the wrong overloading of println!
class Abc {
    public static void main(String[] args) {
        String letters = "ABC";
        Object numbers = new char[] { '1', '2', '3' };
        System.out.print(letters + " easy as ");
        System.out.println(numbers); // Invokes println(Object)
    } 
}

</pre>

To summarize, char arrays are not strings. To convert a char array to a string, invoke String.valueOf(char[]). 
Some library methods do provide stringlike support for char arrays, typically having one overloading for 
Object and another for char[]; only the latter has the desired behavior.

The lesson for language designers is that the char[] type should probably have overridden 
toString to return the characters contained in the array. More generally, the array types 
should probably have overridden toString to return a string representation of the contents of the array.
