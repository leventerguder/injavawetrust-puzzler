package chapter09.puzzle80;

import java.lang.reflect.Constructor;

public class OuterFixed2 {

    public static void main(String[] args) throws Exception {
        new OuterFixed2().greetWorld();
    }

    private void greetWorld() throws Exception {
        System.out.println(OuterFixed2.Inner.class.newInstance());
    }

    public static class Inner {
        public String toString() {
            return "Hello world";
        }
    }
}
