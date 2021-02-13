# Puzzle 46: The Case of the Confusing Constructor

This puzzle presents you with two Confusing constructors. The main method invokes a constructor, but which one? 
The program's output depends on the answer. What does the program print, or is it even legal?

<pre>
public class Confusing {
    private Confusing(Object o) {
        System.out.println("Object");
    }

    private Confusing(double[] dArray) {
        System.out.println("double array");
    }

    public static void main(String[] args) {
        new Confusing(null);
    }
}

</pre>


The parameter passed to the constructor is the null object reference, so at first glance, 
it seems that the program should invoke the Object overloading and print Object. 
On the other hand, arrays are reference types too, so null could just as well apply to the double[] overloading. 
You might therefore conclude that the call is ambiguous, which suggests that the program shouldn't compile. 
If you tried running the program, you found that neither of these intuitions is correct: 
The program prints double array. This behavior may seem perverse, but there is a good reason for it.

Java's overload resolution process operates in two phases. The first phase selects all the methods or constructors 
that are accessible and applicable. The second phase selects the most specific of the methods or constructors selected 
in the first phase. One method or constructor is less specific 
than another if it can accept any parameters passed to the other [JLS 15.12.2.5].


In our program, both constructors are accessible and applicable. 
The constructor Confusing(Object) accepts any parameter passed to Confusing(double[]), 
so Confusing(Object) is less specific. (Every double array is an Object, 
but not every Object is a double array.) The most specific constructor is therefore Confusing(double[]), 
which explains the program's output.


Selecting among overloadings in this fashion is unpleasant. 
In your APIs, ensure that clients aren't forced to go to these extremes. 
Ideally, you should avoid overloading: Use different names for different methods. 
Sometimes, this is not possible. Constructors don't have names, so they can't be given different names.
You can, however, alleviate the problem by making constructors private and providing public static factories [EJ Item 1].
If constructors have many parameters, you can reduce the need for overloading with the Builder pattern [Gamma95].

If you do overload, ensure that all overloadings accept mutually incompatible parameter types, so that 
no two are applicable at the same time. Failing that, 
ensure that all applicable overloadings have the same behavior [EJ Item 26].


In summary, overload resolution can be confusing. Avoid overloading where possible. 
If you must overload, obey the guidelines outlined here to minimize confusion. 
If a poorly designed API forces you to select among overloadings, 
cast actual parameters to the types of the formal parameters of the desired overloading.
