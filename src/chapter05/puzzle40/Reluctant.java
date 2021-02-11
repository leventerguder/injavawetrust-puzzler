package chapter05.puzzle40;

public class Reluctant {

    /*
    When you invoke a constructor, the instance variable initializers
    run before the body of the constructor [JLS 12.5].
     */
    private Reluctant internalInstance = new Reluctant();

    public Reluctant() throws Exception {
        throw new Exception("I'm not coming out");
    }

    public static void main(String[] args) {
        try {
            Reluctant r = new Reluctant();
            System.out.println("Surprise!");
        } catch (Exception ex) {
            System.out.println("I told you so");
        }
    }


    /*
    It is not uncommon for an object to contain instances of its own type.
    This happens, for example, in linked list nodes, tree nodes, and graph nodes.
    You must initialize such contained instances carefully to avoid a StackOverflowError.
     */

    /*
    In summary, instance initializers run before constructor bodies.
    Any exceptions thrown by instance initializers propagate to constructors.
    If initializers throw checked exceptions, constructors must be declared to throw them too,
    but this should be avoided, because it is confusing. Finally, beware of infinite recursion
    when designing classes whose instances contain other instances of the same class.
     */
}
