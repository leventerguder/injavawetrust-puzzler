package chapter09.puzzle76;

public class PingPongFixed {

    public static synchronized void main(String[] a) {
        Thread t = new Thread() {
            public void run() {
                pong();
            }
        };
        t.start();
        System.out.print("Ping");

        /*
        The lesson is simple: Be careful not to invoke a thread's run method
        when you mean to invoke its start method.
         */
    }

    static synchronized void pong() {
        System.out.print("Pong");
    }
}
