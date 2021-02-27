# Puzzle 91: Serial Killer


This program creates an object and checks that it obeys a class invariant. 
Then the program serializes the object, deserializes it, and checks that the deserialized copy also obeys the invariant. 
Does it? If not, why not?


<pre>
import java.io.*;

public class SerialKiller {

    public static void main(String[] args) {

        Sub sub = new Sub(666);
        sub.checkInvariant();
        Sub copy = (Sub) deepCopy(sub);
        copy.checkInvariant();
    }


    // Copies its argument via serialization (See Puzzle 83)
    static public Object deepCopy(Object obj) {
        try {
            ByteArrayOutputStream bos =
                    new ByteArrayOutputStream();
            new ObjectOutputStream(bos).writeObject(obj);
            ByteArrayInputStream bin =
                    new ByteArrayInputStream(bos.toByteArray());
            return new ObjectInputStream(bin).readObject();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

}

</pre>

<pre>
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

class Super implements Serializable {
    final Set<Super> set = new HashSet<Super>();
}

</pre>

<pre>
final class Sub extends Super {
    private int id;

    public Sub(int id) {
        this.id = id;
        set.add(this); // Establish invariant
    }

    public void checkInvariant() {
        if (!set.contains(this))
            throw new AssertionError("invariant violated");
    }

    public int hashCode() {
        return id;
    }

    public boolean equals(Object o) {
        return (o instanceof Sub) && (id == ((Sub) o).id);
    }
}
</pre>

Save for the fact that the program uses serialization, it looks simple. The subclass Sub overrides hashCode and equals. 
The overriding methods satisfy the relevant general contracts [EJ Items 7, 8]. 
The Sub constructor establishes the class invariant, and does so without invoking an overridable method (Puzzle 51). 
The Super class has a single field, of type Set<Super>, and the Sub class adds another field, of type int. 
Neither Super nor Sub requires a custom serialized form. What could possibly go wrong?

<pre>
Exception in thread "main" AssertionError
    at Sub.checkInvariant(SerialKiller.java:41)
    at SerialKiller.main(SerialKiller.java:10)
</pre>

Serializing and deserializing a Sub instance produces a corrupt copy. Why? Looking at the program will not tell you, 
because the real source of the problem lies elsewhere. It is caused by the readObject method of the class HashSet. 
Under certain circumstances, this method can indirectly invoke an overridden method on an uninitialized object. 
In order to populate the hash set that is being deserialized, HashSet.readObject calls HashMap.put, 
which in turn calls hashCode on each key. Because a whole object graph is being deserialized at once, 
there is no guarantee that each key has been completely initialized when its hashCode method is invoked. 
In practice, this is rarely an issue, but occasionally it causes utter chaos. 
The bug is tickled by certain cycles in the object graph that is being deserialized.

To make this more concrete, let us look at what happens when we deserialize the Sub instance in the program. 
First, the serialization system deserializes the Super fields of the Sub instance. The only such field is set, 
which contains a reference to a HashSet. Internally, each HashSet instance contains a reference to a HashMap, 
whose keys are the hash set's elements. The HashSet class has a readObject method that creates an empty HashMap 
and inserts a key-value mapping for each element in the set, using the map's put method. 
This method calls hashCode on the key to determine its bucket. In our program, the sole key in the hash map is the 
Sub instance whose set field is currently being deserialized. The subclass field of this instance, id, 
has yet to be initialized, so it contains 0, the initial value assigned to all int fields. 
Unfortunately, the hashCode method in Sub returns this value instead of 666, which will eventually be stored in this field.

Because hashCode returns the wrong value, the entry for the key-value mapping is placed in the wrong bucket. 
By the time the id field is initialized to 666, it is too late. Changing the value of this field 
once the Sub instance is in the HashMap corrupts it, which corrupts the HashSet, which corrupts the Sub instance. 
The program detects this corruption and throws an appropriate error.

This program illustrates that the serialization system as a whole, which includes the readObject method of HashMap, 
violates the rule that you must not invoke an overridable method of a class from its constructor or pseudoconstructor [EJ Item 15]. 
The (default) readObject method of the class Super invokes the (explicit) readObject method of the class HashSet, 
which invokes the put method on its internal HashMap, which invokes the hashCode method on the Sub instance 
that is currently in the process of creation. Now we are in big trouble: The hashCode method that Super inherits from 
Object is overridden in Sub, and this overridden method executes before the initialization of the Sub field, on which it depends.

This failure is nearly identical in nature to the one in Puzzle 51. The only real difference is that in this puzzle, 
the readObject pseudoconstructor is at fault instead of the constructor.
The readObject methods of HashMap and Hashtable are similarly affected.

For platform implementers, it may be possible to fix this problem in HashSet, HashMap, and HashTable at a slight performance penalty. 
The strategy, as it applies to HashSet, is to rewrite the readObject method to store the set's elements in an array instead of 
putting them in the hash set at deserialization time. Then, on the first invocation of a public method on the deserialized hash set, 
the elements in the array would be inserted into the set before executing the method.


The cost of this approach is that it requires checking whether to populate the hash set on entry to each of its public methods. 
Because HashSet, HashMap, and HashTable are all performance-critical, this approach seems undesirable. 
It is unfortunate that all users would have to pay the cost, even if they did not serialize the collections. 
This violates the tenet that you should never have to pay for functionality that you don't use.

Another possible approach would be for HashSet.readObject to call ObjectInputStream.registerValidation and to 
delay population of the hash set until the validateObject callback. This approach seems more attractive in that it 
adds cost only to deserialization, but it would break any code that tried to use a deserialized HashSet instance 
while deserialization of the containing stream was still in progress.


Whether either of these approaches is practical remains to be seen. In the meantime, we must live with the current behavior. 
Luckily, there is a workaround: If a HashSet, Hashtable, or HashMap will be serialized, 
ensure that its contents do not refer back to it, directly or indirectly. 
By contents, we mean elements, keys, and values.


There is also a lesson for developers of serializable classes: In readObject or readResolve methods, 
avoid invoking methods directly or indirectly on objects currently being deserialized. 
If you must violate this advice in the readObject or readResolve method for some class C, 
ensure that no instance of C appears in a cycle in the graph of objects being deserialized. 
Unfortunately, this is not a local property: In general, you must consider the whole system in order to verify it.


In summary, the Java serialization system is fragile. In order to serialize many classes correctly and efficiently, 
you must write readObject or readResolve methods [EJ Items 55 57]. 
This puzzle demonstrates that you must write these methods carefully in order to avoid corruption of deserialized instances. 
The readObject methods of HashSet, HashMap, and Hashtable are susceptible to corruption. 
For platform designers, if you choose to provide a serialization system, try to design one that is not so fragile. 
Robust serialization systems are notoriously difficult to design.
