# Puzzle 83: Dyslexic Monotheism

Once upon a time, there was a man who thought there was only one exceptional dog, 
so he wrote the following class, which he took to be a singleton [Gamma95]:

<pre>
public class Dog extends Exception {
    
    public static final Dog INSTANCE = new Dog();

    private Dog() {
    }

    public String toString() {
        return "Woof";
    }
}
</pre>

Can you create a second Dog instance from outside this class without using reflection?

This class may look like a singleton, but it isn't. The problem is that Dog extends Exception and Exception implements 
java.io.Serializable. This means that Dog is serializable, and deserialization constitutes a hidden constructor. 
If you serialize Dog.INSTANCE and deserialize the resulting byte sequence, you will end up with another Dog, 
as demonstrated by the following program. It prints false, indicating that the new Dog instance is distinct 
from the original, and Woof, indicating that the new Dog instance is functional:

<pre>
import java.io.*;

public class CopyDog {

    public static void main(String[] args) {


        Dog newDog = (Dog) deepCopy(Dog.INSTANCE);
        System.out.println(newDog == Dog.INSTANCE);
        System.out.println(newDog);

    }


    // This method is very slow and generally a bad idea!
    public static Object deepCopy(Object obj) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            new ObjectOutputStream(bos).writeObject(obj);
            ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
            return new ObjectInputStream(bin).readObject();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

}
</pre>


To fix the problem, add a readResolve method to Dog, which turns the hidden constructor into a hidden 
static factory that returns the one true Dog [EJ Items 2, 57]. With the addition of this method to Dog, 
CopyDog will print true instead of false, indicating that the "copy" is in fact the original:

<pre>
private Object readResolve() {
    // Accept no substitutes!
    return INSTANCE;
}

</pre>

The main lesson of this puzzle is that a singleton class that implements Serializable must 
have a readResolve method that returns its sole instance. A secondary lesson is that it is possible to 
implement Serializable unintentionally, by extending a class that implements Serializable or 
by implementing an interface that extends Serializable. A lesson for platform designers is that hidden constructors, 
such as the one provided by serialization, can harm the reader's intuition about program behavior.

