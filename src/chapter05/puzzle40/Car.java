package chapter05.puzzle40;

public class Car {

    /*
    In summary, instance initializers run before constructor bodies.
    Any exceptions thrown by instance initializers propagate to constructors.
    If initializers throw checked exceptions, constructors must be declared to throw them too,
    but this should be avoided, because it is confusing.
     */

    private static Class engineClass = Engine.class;
    private Engine engine = (Engine) engineClass.newInstance();

    // throws must be defined!
    public Car() throws IllegalAccessException, InstantiationException {

    }

}
