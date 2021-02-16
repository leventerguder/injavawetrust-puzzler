# Puzzle 56: Big Problem

What does the program print?

<pre>
import java.math.BigInteger;

public class BigProblem {
    public static void main(String[] args) {

        BigInteger fiveThousand = new BigInteger("5000");
        BigInteger fiftyThousand = new BigInteger("50000");
        BigInteger fiveHundredThousand
                = new BigInteger("500000");
        BigInteger total = BigInteger.ZERO;
        total.add(fiveThousand);
        total.add(fiftyThousand);
        total.add(fiveHundredThousand);

        System.out.println(total); // 0

    }
}

</pre>


You might think that this program prints555000. After all, it setstotalto theBigInteger representation for 0 and then 
adds 5,000, 50,000, and 500,000. If you ran the program, you found that it doesn't print 555000 but 0. 
Apparently all that addition has no effect on total.


There is a good reason for this: BigInteger instances are immutable. So are instances of String, BigDecimal, 
and the wrapper types: Integer, Long, Short, Byte, Character, Boolean, Float, andDouble. You can't change their values. 
Instead of modifying existing instances, operations on these types return new instances. 
At first, immutable types might seem unnatural, but they have many advantages over their mutable counterparts. 
Immutable types are easier to design, implement, and use; they are less error prone and more secure [EJ Item 13].


To perform a computation on a variable containing a reference to an immutable object, 
assign the result of the computation to the variable.


<pre>
import java.math.BigInteger;

public class BigProblemFixed {
    public static void main(String[] args) {

        BigInteger fiveThousand = new BigInteger("5000");
        BigInteger fiftyThousand = new BigInteger("50000");
        BigInteger fiveHundredThousand
                = new BigInteger("500000");

        BigInteger total = BigInteger.ZERO;
        total = total.add(fiveThousand);
        total = total.add(fiftyThousand);
        total = total.add(fiveHundredThousand);

        System.out.println(total); // 0

    }
}

</pre>

The lesson of this puzzle is: Do not be misled into thinking that immutable types are mutable. 
This is a common error among beginning Java programmers. In fairness, the names of some methods in Java's immutable 
types help to lead them astray. Names like add, subtract, and negate suggest that these methods mutate the instance 
on which they're invoked. Better names would be plus, minus, and negation.


The main method of the program creates two Name instances, both representing Mickey Mouse. 
The program puts the first instance into a hash set and then checks whether the set contains the second. 
The two Name instances are equal, so it might seem that the program should print true. 
If you ran it, it almost certainly printed false. What is wrong with the program?


The bug is that Name violates the hashCode contract. 
This might seem strange, as Name doesn't even have a hashCode method, but that is precisely the problem. 
The Name class overrides the equals method, and the hashCode contract demands that equal objects have equal hash codes. 
To fulfill this contract, you must override hashCode whenever you override equals [EJ Item 8].

Because it fails to override hashCode, the Name class inherits its hashCode implementation from Object. 
This implementation returns an identity-based hash code. In other words, distinct objects are 
likely to have unequal hash values, even if they are equal. Name does not fulfill the hashCode contract, 
so the behavior of a hash set containing Name elements is unspecified.


When the program puts the first Name instance into the hash set, the set puts an entry for this instance into a hash bucket. 
The set chooses the hash bucket based on the hash value of the instance, as computed by its hashCode method. 
When it checks whether the second Name instance is contained in the hash set, 
the program chooses which bucket to search based on the hash value of the second instance.
Because the second instance is distinct from the first, it is likely to have a different hash value. 
If the two hash values map to different buckets, the contains method will return false: 
The beloved rodent is in the hash set, but the set can't find him.


Suppose that the two Name instances map to the same bucket. Then what? All HashSet implementations 
that we know of have an optimization in which each entry stores the hash value of its element in addition to the element itself. 
When searching for an element, the implementation selects the appropriate hash bucket and traverses its entries, 
comparing the hash value stored in each entry with the hash value of the desired element.
Only if the two hash values are equal does the implementation check the elements for equality.
This optimization makes sense because it is usually much cheaper to compare hash codes than elements.


To fix the problem, simply add an appropriate hashCode method to the Name class. 
Although any method whose return value is determined solely by the first and last name will satisfy the contract, 
a high-quality hash function should attempt to return different hash values for different names. 
The following method will do nicely [EJ Item 8]. Once this method is added, the program will print true as expected:

<pre>
public int hashCode() {
    return 37 * first.hashCode() + last.hashCode();
}
</pre>


In summary, always override hashCode when you override equals. More generally, 
obey the general contract when you override a method that has one. 
This is an issue for most of the non-final methods declared in Object [EJ Chapter 3]. 
Failure to follow this advice can result in arbitrary, unspecified behavior.
