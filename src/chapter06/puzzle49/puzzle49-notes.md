# Puzzle 49: Larger Than Life

The program uses the idiom Calendar.getInstance().get(Calendar.YEAR), 
which returns the current calendar year. What does the program print?

<pre>
public class Elvis {

    public static final Elvis INSTANCE = new Elvis();

    private final int beltSize;
    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    private Elvis() {
        beltSize = CURRENT_YEAR - 1930;
    }

    public int beltSize() {
        return beltSize;
    }

    public static void main(String[] args) {
        System.out.println("Elvis wears a size " + INSTANCE.beltSize() + " belt.");
    }

}

</pre>

It prints Elvis wears a size -1930 belt

This program suffers a problem caused by a circularity in the order of class initialization [JLS 12.4].
Initialization of the class Elvis is triggered by the VM's call to its main method. 
First, static fields are set to their default values [JLS 4.12.5]. 
The field INSTANCE is set to null, and CURRENT_YEAR is set to 0. Next, static field initializers are executed 
in order of appearance. The first static field is INSTANCE. Its value is computed by invoking the Elvis() constructor.


The constructor initializes beltSize to an expression involving the static field CURRENT_YEAR. 
Normally, reading a static field is one of the things that causes a class to be initialized, 
but we are already initializing the class Elvis. Recursive initialization attempts are simply ignored [JLS 12.4.2, step 3]. 
Consequently, the value of CURRENT_YEAR still has its default value of 0. That is why Elvis's belt size turns out to be -1930.

Finally, returning from the constructor to complete the class initialization of Elvis, 
we initialize the static field CURRENT_YEAR to 2006, assuming you're running the program in 2006. 
Unfortunately, it is too late for the now correct value of this field to affect the computation of Elvis.INSTANCE.beltSize, 
which already has the value -1930. This is the value that will be returned by all subsequent calls to Elvis.INSTANCE.beltSize().


This program shows that it is possible to observe a final static field before it is initialized, 
when it still contains the default value for its type. That is counterintuitive, 
because we usually think of final fields as constants. 
Final fields are constants only if the initializing expression is a constant expression [JLS 15.28]

Problems arising from cycles in class initialization are difficult to diagnose but once diagnosed are usually easy to fix.
To fix a class initialization cycle, reorder the static field initializers so that each initializer appears before any 
initializers that depend on it. In this program, the declaration for CURRENT_YEAR belongs before the declaration for INSTANCE, 
because the creation of an Elvis instance requires that CURRENT_YEAR be initialized. Once the declaration 
for CURRENT_YEAR has been moved, Elvis will indeed be larger than life.


Some common design patterns are naturally subject to initialization cycles, notably the Singleton [Gamma95], 
which is illustrated in this puzzle, and the Service Provider Framework [EJ Item 1]. 
The Typesafe Enum pattern [EJ Item 21] also causes class initialization cycles. 


In summary, be careful of class initialization cycles. The simplest ones involve only a single class, 
but they can also involve multiple classes. It isn't always wrong to have class initialization cycles, 
but they may result in constructor invocation before static fields are initialized. Static fields, even final static fields, 
may be observed with their default value before they are initialized.
