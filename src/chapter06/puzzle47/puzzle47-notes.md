# Puzzle 47: Well, Dog My Cats!


This program uses a Counter class to keep track of how many times each kind of house pet makes a noise. 
What does the program print?

<pre>
public class Counter {

    private static int count;

    public static void increment() {
        count++;
    }

    public static int getCount() {
        return count;
    }
}

</pre>

<pre>
class Cat extends Counter {
    public Cat() {
    }

    public void meow() {
        increment();
    }
}

</pre>


<pre>
class Dog extends Counter {
    public Dog() {
    }

    public void woof() {
        increment();
    }
}

</pre>

<pre>
public class Ruckus {

    public static void main(String[] args) {

        Dog dogs[] = {new Dog(), new Dog()};

        for (int i = 0; i < dogs.length; i++)
            dogs[i].woof();

        Cat cats[] = {new Cat(), new Cat(), new Cat()};
        for (int i = 0; i < cats.length; i++)
            cats[i].meow();

        System.out.print(Dog.getCount() + " woofs and ");
        System.out.println(Cat.getCount() + " meows");

    }
}

</pre>

We have two dogs woofing and three cats meowing a ruckus, to be sure so the program should print 2 woofs and 3 meows, 
no? No: It prints 5 woofs and 5 meows. Where is all the extra noise coming from, and what can we do to stop it?

The problem is that Dog and Cat inherit the count field from a common superclass, and count is a static field.
A single copy of each static field is shared among its declaring class and all subclasses, 
so Dog and Cat use the same count field. Each call to woof or meow increments this field, so it is incremented five times. 
The program reads it twice, by calling Dog.getCount and Cat.getCount. In each case, 5 is returned and printed.

When designing one class to build on the behavior of another, you have two options: inheritance, 
in which one class extends the other; or composition, in which one class contains an instance of the other. 
Choose based on whether each instance of one class is an instance of the other class or has an instance of the other. 
In the first case, use inheritance; in the second, use composition. 
When in doubt, favor composition over inheritance [EJ Item 14].


Neither a dog nor a cat is a kind of counter, so it was wrong to use inheritance. 
Instead of extending Counter, Dog and Cat should each have a counter field. 
One counter is required for each kind of pet, rather than for each individual pet, 
so these fields must be static. We needn't bother with a Counter class; an int will do fine. 


In summary, static fields are shared by their declaring class and any subclasses. 
If you need a separate copy of a field for each subclass, you must declare a separate static field in each subclass. 
If you need a separate copy for each instance, declare a nonstatic field in the base class. 
Also, favor composition over inheritance unless the derived class really is a kind of the base class.
