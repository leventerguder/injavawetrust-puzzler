package chapter08.puzzle74;

final class Enigma {
    // Don't do this!
//    public boolean equals(Enigma other) {
//        return false;
//    }

    /*
    Arguably, this solution violates the spirit of the puzzle: The println invocation that produces the desired output
    appears in the Enigma constructor, not the main method. Still, it does solve the puzzle, and you have to admit it's cute.

     */
    public Enigma() {
        System.out.println(false);
        System.exit(0);
    }
}
