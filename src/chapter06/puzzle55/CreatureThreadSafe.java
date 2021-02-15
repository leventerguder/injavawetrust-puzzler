package chapter06.puzzle55;


// Thread-safe creation counter
public class CreatureThreadSafe {

    private static long numCreated;

    public CreatureThreadSafe() {
        synchronized (Creature.class) {
            numCreated++;
        }
    }

    public static synchronized long numCreated() {
        return numCreated;
    }
}
