# Puzzle 51: What's the Point?

<pre>
public class Point {
    private final int x, y;
    private final String name; // Cached at construction time

    Point(int x, int y) {
        this.x = x;
        this.y = y;
        name = makeName();
    }

    protected String makeName() {
        return "[" + x + "," + y + "]";
    }

    public final String toString() {
        return name;
    }
}

</pre>


<pre>
public class ColorPoint extends Point{

    private final String color;

    ColorPoint(int x, int y, String color) {
        super(x, y);
        this.color = color;
    }

    protected String makeName() {
        return super.makeName() + ":" + color;
    }

     public static void main(String[] args) {
        System.out.println(new ColorPoint(4, 2, "purple"));
    }
}

</pre>


If you ran the program, you found that it prints [4,2]:null. What is the matter with the program?
The program suffers from a problem with the order of instance initialization. 

First, the program creates a ColorPoint instance by invoking the ColorPoint constructor (1). 
(new ColorPoint(4, 2, "purple") 
This constructor starts by chaining to the superclass constructor, as all constructors do (2).
super(x, y);        // 2. Chain to Point constructor

The superclass constructor assigns 4 to the x field of the object under construction and 2 to its y field. 
Then the constructor invokes makeName, which is overridden by the subclass (3).
name = makeName(); // 3. Invoke subclass method

The makeName method in ColorPoint (4) executes before the body of the ColorPoint constructor, 
and therein lies the heart of the problem. The makeName method first invokes super.makeName, 
which returns [4,2] as expected. Then the method appends the string ":" and the value of the color field, 
converted to a string. But what is the value of the color field at this point? It has yet to be initialized, 
so it still contains its default value of null. Therefore, the makeName method returns the string "[4,2]:null". 
The superclass constructor assigns this value to the name field (3) and returns control to the subclass constructor.

The subclass constructor then assigns the value "purple" to the color field (5), but it is too late. 
The color field has already been used to initialize the name field in the superclass to an incorrect value. 
The subclass constructor returns, and the newly created ColorPoint instance is passed to the println method, 
which duly invokes its toString method. This method returns the contents of its name field, "[4,2]:null", 
so that is what the program prints.

this.color = color; // 5. Initialize blank final-Too late

This puzzle illustrates that it is possible to observe the value of a final instance field before its value 
has been assigned, when it still contains the default value for its type. In a sense, this puzzle is the instance 
analog of Puzzle 49, which observed the value of a final static field before its value had been assigned. 
In both cases, the puzzle resulted from a circularity in initialization. In Puzzle 49, 
it was class initialization; in this puzzle, it is instance initialization. 
Both cases have the potential for enormous confusion. 
There is one point where the analogy breaks down: Circular class initialization is a necessary evil,
but circular instance initialization can and should always be avoided.


The problem arises whenever a constructor calls a method that has been overridden in its subclass. 
A method invoked in this way always runs before the instance has been initialized, 
when its declared fields still have their default values. To avoid this problem, 
never call overridable methods from constructors, either directly or indirectly [EJ Item 15].


You can fix the problem by initializing the field name lazily, when it is first used, rather than eagerly, 
when the Point instance is created. With this change, the program prints [4,2]:purple as expected:

<pre>
public class Point {

    protected final int x, y;
    private String name; // Lazily initialized


    Point(int x, int y) {
        this.x = x;
        this.y = y;
        // name initialization removed
    }

    protected String makeName() {
        return "[" + x + "," + y + "]";
    }

    // Lazily computes and caches name on first use
    public final synchronized String toString() {
        if (name == null)
            name = makeName();
        return name;
    }
}
</pre>


To summarize, you must never call an overridable method from a constructor under any circumstances. 
The resulting circularities in instance initialization can be fatal. The solution to this problem is 
lazy initialization [EJ Items 13, 48].
