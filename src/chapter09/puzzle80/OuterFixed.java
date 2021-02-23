package chapter09.puzzle80;

import java.lang.reflect.Constructor;

public class OuterFixed {

    public static void main(String[] args) throws Exception {
        new OuterFixed().greetWorld();
    }

    private void greetWorld() throws Exception {
        Constructor c = Inner.class.getConstructor(OuterFixed.class);
        System.out.println(c.newInstance(OuterFixed.this));

    }

    public class Inner {
        public String toString() {
            return "Hello world";
        }
    }
}
