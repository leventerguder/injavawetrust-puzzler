package chapter09.puzzle81;

public class GreeterFixed {

    public static void main(String[] args) {

        String greeting = "Hello world";
        for (int i = 0; i < greeting.length(); i++)
            System.out.write(greeting.charAt(i));

        // The simplest change that fixes the program is to add a System.out.flush invocation after the loop.
        System.out.flush();
    }
}
