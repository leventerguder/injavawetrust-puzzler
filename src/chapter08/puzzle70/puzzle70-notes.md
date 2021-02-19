# Puzzle 70: Package Deal

The main method is in class hack.TypeIt. What does the program print?

<pre>

package hack;

import click.CodeTalk;

public class TypeIt {

    private static class ClickIt extends CodeTalk {

        void printMessage() {
            System.out.println("Hack");
        }
    }

    public static void main(String[] args) {
        ClickIt clickit = new ClickIt();
        clickit.doIt();
    }

}

</pre>


<pre>

package click;

public class CodeTalk {

    public void doIt() {
        printMessage();
    }

    void printMessage() {
        System.out.println("Click");
    }
}

</pre>

The main method in hack.TypeIt instantiates the class TypeIt.ClickIt and invokes its doIt method, which is inherited 
from CodeTalk. This method, in turn, calls printMessage, which is declared in TypeIt.ClickIt to print Hack. And yet, 
if you run the program, it prints Click. How can this be?

This analysis incorrectly assumes that hack.TypeIt.ClickIt.printMessage overrides click.CodeTalk.printMessage. 
A package-private method cannot be directly overridden by a method in a different package [JLS 8.4.8.1]. 

When the program calls printMessage from within the package hack, 
the package-private method hack.TypeIt.ClickIt.printMessage is run

If you want the printMessage method in hack.TypeIt.ClickIt to override the method in click.CodeTalk, 
you must add the protected or public modifier to the method declaration in click.CodeTalk.

In summary, package-private methods cannot be directly overridden outside the package in which they're declared. 
Although the combination of package-private access and overriding can lead to some confusion, 
Java's current behavior enables packages to support encapsulation of abstractions larger than a single class. 
Package-private methods are implementation details of their package, and reuse of 
their names outside the package should have no effect inside the package.

