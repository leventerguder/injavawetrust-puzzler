# Puzzle 73: Your Privates Are Showing

The idea behind private members methods, fields, and types is that they're simply implementation details: 
The implementer of a class can feel free to add new ones and change or remove old ones without fear of harming 
clients of the class. In other words, private members are fully encapsulated by the class that contains them.


Making a class serializable and accepting the default serialized form causes the class's private instance fields 
to become part of its exported API [EJ Items 54, 55]. Changes in the private representation can then lead to exceptions 
or erratic behavior when clients use existing serialized objects.

But what about compile-time errors? Can you write a final "library" class and a "client" class, 
both of which compile without error, and then add a private member to the library 
class so that it still compiles but the client class no longer does?


If your solution involves adding a private constructor to the library class to suppress the creation of 
a default public constructor, give yourself half a point. 
The puzzle required you to add a private member and, strictly speaking, constructors aren't members [JLS 6.4.3].

<pre>

package library;

public class Api {
    // private static class String {}
    public static String newString() {
        return new String();
    }
}

</pre>


<pre>
package client;

public class Client {

    String s = Api.newString();
}

</pre>

As written, the program compiles without error. If we uncomment the private declaration of the 
local class String in library.Api, the method Api.newString no longer has the return type java.lang.String, 
so the initialization of the variable Client.s fails to compile:


Although the only textual change we made was to add a private class declaration, we indirectly changed the return 
type of an existing public method, which is an incompatible API change. 
We changed the meaning of a name used in our exported API.

<pre>
package library;
class ApiBase {
    public static final int ANSWER = 42;
}
</pre>

<pre>
public final class Api extends ApiBase {
    // private static final int ANSWER = 6 * 9;
}

</pre>

<pre>
package client;
import library.Api;
public class Client {
    int answer = Api.ANSWER;
}
</pre>

As written, this program compiles without error. If we uncomment the private declaration in library.Api, 
the client fails to compile:


The new private field Api.ANSWER hides the public field ApiBase.ANSWER, which would otherwise be inherited into Api. 
Because the new field is declared private, it can't be accessed from Client. Many variations on this solution are possible. 
You can hide an instance field instead of a static field, or a type instead of a field.


You can also solve this puzzle with obscuring. All the solutions involve reusing a name to break the client. 
Reusing names is dangerous; avoid hiding, shadowing, and obscuring. 
