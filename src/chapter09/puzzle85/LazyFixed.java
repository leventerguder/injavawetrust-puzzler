package chapter09.puzzle85;

// Bad way to eliminate the deadlock. Complex and error prone.
public class LazyFixed {

    private static boolean initialized = false;
    private static Thread t = new Thread(new Runnable() {
        public void run() {
            initialized = true;
        }
    });

    static {
        t.start();
    }

    public static void main(String[] args) {

        try {
            t.join();
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }

        System.out.println(initialized);
    }

}
