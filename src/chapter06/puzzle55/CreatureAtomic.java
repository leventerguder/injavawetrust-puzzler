package chapter06.puzzle55;

import java.util.concurrent.atomic.AtomicLong;

// Thread-safe creation counter using AtomicLong;
public class CreatureAtomic {

    private static AtomicLong numCreated = new AtomicLong();

    public CreatureAtomic() {
        numCreated.incrementAndGet();
    }

    public static long numCreated() {
        return numCreated.get();
    }
}
