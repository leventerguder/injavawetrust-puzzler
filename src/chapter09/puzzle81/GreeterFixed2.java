package chapter09.puzzle81;

public class GreeterFixed2 {

    public static void main(String[] args) {

        String greeting = "Hello world";
        for (int i = 0; i < greeting.length(); i++)
            System.out.print(greeting.charAt(i));

        // It would, however, be far better to rewrite the program to use the more familiar
        // System.out.println idiom for producing output on the console.
    }
}
