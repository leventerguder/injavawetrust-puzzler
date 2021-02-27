package chapter10.puzzle91;

final class Sub extends Super {
    private int id;

    public Sub(int id) {
        this.id = id;
        set.add(this); // Establish invariant
    }

    public void checkInvariant() {
        if (!set.contains(this))
            throw new AssertionError("invariant violated");
    }

    public int hashCode() {
        return id;
    }

    public boolean equals(Object o) {
        return (o instanceof Sub) && (id == ((Sub) o).id);
    }
}
