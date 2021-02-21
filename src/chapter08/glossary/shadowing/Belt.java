package chapter08.glossary.shadowing;

class Belt {
    private final int size;

    public Belt(int size) { // Parameter shadows Belt.size
        this.size = size;
    }
}
