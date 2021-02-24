package chapter09.puzzle84;

public class SelfInterruption {

    public static void main(String[] args) {

        Thread.currentThread().interrupt();

        /*
        Calling Thread.interrupted always clears the interrupted status of the current thread.
         */
        if (Thread.interrupted()) {
            System.out.println("Interrupted: " + Thread.interrupted());
        } else {
            System.out.println("Not interrupted: " + Thread.interrupted());
        }
    }
}
