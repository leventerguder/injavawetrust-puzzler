# Puzzle 43: Exceptionally Unsafe


In JDK 1.2, Thread.stop, Thread.suspend, and a few other thread-related methods were deprecated because 
they are unsafe [ThreadStop]. The following method demonstrates one of the 
horrible things you could do with Thread.stop:

<pre>
// Don't do this - circumvents exception checking!
public static void sneakyThrow(Throwable t) {
    Thread.currentThread().stop(t); // Deprecated!!
}

</pre>



One solution to this puzzle takes advantage of a design deficiency in the Class.newInstance method, 
which instantiates a class reflectively. 

One solution to this puzzle takes advantage of a design deficiency in the Class.newInstance method, 
which instantiates a class reflectively. 


<pre>
public class Thrower {
    
    private static Throwable t;

    private Thrower() throws Throwable {
        throw t;
    }

    public static synchronized void sneakyThrow(Throwable t) {
        Thrower.t = t;
        try {
            Thrower.class.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException();
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException();
        } finally {
            Thrower.t = null; // Avoid memory leak
        }
    }
}
</pre>


A few subtle things are going on in this solution. 
The exception to be thrown during constructor execution can't be passed to the constructor as a parameter, 
because Class.newInstance invokes a class's parameterless constructor.

Therefore, the sneakyThrow method stashes this exception in a static variable. 
To make the method thread-safe, it must be synchronized. 
This causes concurrent invocations to take turns using the static t field.


Note that the t field is nulled out in a finally block: Just because the method 
is sneaky doesn't mean it should also be leaky. If this field weren't nulled out, 
it would prevent the exception from being garbage collected. Finally, note that 
the method will fail with an IllegalArgumentException if you ask it to throw an InstantiationException 
or IllegalAccessException. This is an inherent limitation of the technique.

The documentation for Class.newInstance goes on to say that "the Constructor.newInstance 
method avoids this problem by wrapping any exception thrown by the constructor in a (checked) 
InvocationTargetException." Clearly, Class.newInstance should have done the same thing, 
but it's far too late to correct this deficiency. Doing so would introduce a source-level incompatibility,
breaking the many programs that depend on Class.newInstance. It would not be practical to deprecate 
his method either, because it is so commonly used. Just be aware when you use it that Class.newInstance 
can throw checked exceptions that it does not declare

Generics, which were added in release 5.0, enable a completely different solution to this puzzle. 
For maximal compatibility generics are implemented by type erasure: Generic type information is checked 
at compile time but not at run time [JLS 4.7]. The following solution exploits this

<pre>
// Don't do this either - circumvents exception checking!
class TigerThrower<T extends Throwable> {
    public static void sneakyThrow(Throwable t) {
        new TigerThrower<Error>().sneakyThrow2(t);
    }

    private void sneakyThrow2(Throwable t) throws T {
        throw (T) t;
    }
}
</pre>


A warning is the compiler's way of telling you that you may be shooting yourself in the foot, 
and in fact you are. The unchecked cast warning tells you that the cast in question 
will not be checked at run time. When you get an unchecked cast warning, modify your 
program to eliminate it, or convince yourself that the cast cannot fail. If you don't, 
some other cast may fail at an undetermined time in the future, and you may have a
hard time tracing the error to its source. In this case, it's even worse: 
The exception that is thrown at run time may not conform to the signature of the method. 
The sneakyThrow2 method exploits this methodically.

In summary, Java's exception checking is not enforced by the virtual machine. 
It is a compile-time facility designed to make it easier to write correct programs,
but it can be circumvented at run time. To reduce your exposure, do not ignore compiler warnings.
