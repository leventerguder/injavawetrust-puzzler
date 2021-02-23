# Puzzle 79: It's a Dog's Life

This class models the life of a house pet. The main method creates a Pet instance representing 
a dog named Fido and lets it run. Although most dogs run in the backyard, 
this one runs in the background. What does the program print?

<pre>
package chapter09.puzzle79;

public class Pet {

    public final String name;
    public final String food;
    public final String sound;

    public Pet(String name, String food, String sound) {
        this.name = name;
        this.food = food;
        this.sound = sound;
    }

    public void eat() {
        System.out.println(name + ": Mmmmm, " + food);
    }

    public void play() {
        System.out.println(name + ": " + sound + " " + sound);
    }

    public void sleep() {
        System.out.println(name + ": Zzzzzzz...");
    }

    public void live() {
        new Thread() {
            public void run() {
                while (true) {
                    eat();
                    play();
                    sleep();
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        new Pet("Fido", "beef", "Woof").live();
    }
}

</pre>

If you tried the program, you found that it won't even compile. The compiler error is less than helpful:

<pre>
symbol: method sleep()
sleep();
</pre>


Why can't the compiler find the symbol? It's right there in black and white. 
As in Puzzle 74, the problem stems from the details of the overload resolution process. 
The compiler searches for the method in the innermost enclosing scope containing a method with the correct name [JLS 15.12.1].

The obvious way to fix the program is to change the name of the sleep method in Pet to snooze, doze, or nap. 
Another way to fix the problem is to name the class explicitly in the method invocation, using the qualified 
his construct [JLS 15.8.4]. The resulting invocation is Pet.this.sleep().


use the Thread(Runnable) constructor instead of extending Thread. 
If you do this, the problem goes away because the anonymous class does not inherit Thread.sleep.

<pre>
    public void live() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    eat();
                    play();
                    sleep();
                }
            }
        }).start();
    }
</pre>

In summary, beware of unintentional shadowing, and learn to recognize compiler errors that indicate its presence. 
For compiler writers, do your best to generate error messages that are meaningful to the programmer. 
