package chapter05.puzzle40;

public class CarFixed {

    private static Class engineClass = Engine.class;

    private static Engine newEngine() {

        try {
            return (Engine) engineClass.newInstance();
        } catch (InstantiationException e) {
            throw new AssertionError(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    // // Fixed - instance initializers don't throw checked exceptions
    public CarFixed() {

    }
}
