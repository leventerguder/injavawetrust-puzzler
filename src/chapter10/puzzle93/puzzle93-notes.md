# Puzzle 93: Class Warfare


This puzzle tests your knowledge of binary compatibility: What happens to the behavior of one class 
when you change another class on which the first class depends? More specifically, 
suppose that you compile the following two classes. The first is meant to represent a client; the second, a library class:

<pre>
public class PrintWords {

    public static void main(String[] args) {

        System.out.println(Words.FIRST  + " " +
                Words.SECOND + " " +
                Words.THIRD);

    }
}

</pre>


<pre>
public class Words {

    // Uninstantiable
    private Words() {
    }


    public static final String FIRST = "the";
    public static final String SECOND = null;
    public static final String THIRD = "set";

}

</pre>


Now suppose that you modify the library class as follows and recompile it but not the client program:

<pre>
public class Words {
    private Words() { };  // Uninstantiable
    public static final String FIRST  = "physics";
    public static final String SECOND = "chemistry";
    public static final String THIRD  = "biology";
}
</pre>


A quick look suggests that the program should print physics chemistry biology; after all, Java loads classes at run time, 
so it always has access to the latest version of a class. A deeper analysis suggests otherwise. 
References to constant fields are resolved at compile time to the constant values they denote [JLS 13.1]. 
Such fields are technically, if oxymoronically, known as constant variables. 
A constant variable is defined as a variable of primitive type or type String that is final and initialized 
with a compile-time constant expression [JLS 4.12.4]. With the benefit of this knowledge, it would be reasonable 
to think that the client program compiles the initial values of Words.FIRST, Words.SECOND, and Words.
THIRD into its class file and prints the null set, regardless of whether the class Words has been modified.


Reasonable, perhaps, but not correct. If you ran the program, you found that it prints the chemistry set. 
This seems truly bizarre. Why would it do a thing like that? 
The answer is to be found in the precise definition of the term compile-time constant expression [JLS 15.28]. 
The definition is too long to reproduce here, but the key to understanding the behavior of 
the program is that null is not a compile-time constant expression.


Because constant fields are compiled into clients, API designers should think long and hard before exporting a constant field. 
If a field represents a true constant, such as π or the number of days in a week, there is no harm in making it a constant field. 
If, however, you want clients to adapt to changes in the field, make sure that it isn't a constant.
There is an easy way to do this: If you initialize a field, even a final field, with an expression that isn't constant, 
the field isn't constant. You can turn a constant expression into a nonconstant by passing it to a method that simply returns its input parameter.

Despite their name, enum constants, introduced in release 5.0, are not constant variables. 
You can add enum constants to an enum type, reorder them, and even remove unused enum constants without the need to recompile clients.

In summary, constant variables are compiled into classes that reference those variables. 
A constant variable is any primitive or string variable that is initialized with a constant expression. 
Surprisingly, null is not a constant expression.

For language designers, perhaps it is not such a good idea to compile constant expressions into clients in 
a language that is otherwise dynamically linked. It is surprising to many programmers and can produce bugs 
that are difficult to diagnose: The source code in which constants were defined may no longer exist when a bug is detected. 
On the other hand, compiling constant expressions into clients does enable the use of if statements to 
emulate conditional compilation [JLS 14.21]. It is a matter of judgment whether the end justifies the means.
