package chapter08.puzzle73.library;

public class Api extends ApiBase{

    // private static class String {}

    public static String newString() {
        return new String();
    }

    /*
    The new private field Api.ANSWER hides the public field ApiBase.ANSWER, which would otherwise be inherited into Api.
    Because the new field is declared private, it can't be accessed from Client.
    Many variations on this solution are possible.
    sYou can hide an instance field instead of a static field, or a type instead of a field.
     */
    //private static final int ANSWER = 6 * 9;
}
