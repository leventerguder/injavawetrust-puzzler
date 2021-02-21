package chapter08.glossary.obscuring;

public class Obscure {
    static String System; // Obscures type java.lang.System

    public static void main(String[] args) {
        // Next line won't compile: System refers to static field
        // System.out.println("hello, obscure world!");
    }
}
