package chapter09.puzzle84;

public class SelfInterruptionFixed {

    public static void main(String[] args) {
        
        Thread.currentThread().interrupt();

        if (Thread.currentThread().isInterrupted()) {
            System.out.println("Interrupted: " + Thread.interrupted());
        } else {
            System.out.println("Not interrupted: " + Thread.interrupted());
        }
    }
}
