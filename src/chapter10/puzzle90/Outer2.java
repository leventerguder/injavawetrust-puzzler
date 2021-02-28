package chapter10.puzzle90;

public class Outer2 {
    class Inner1 extends Outer2 {
        public Inner1() {
            super();  // invokes Object() constructor
        }
    }

    class Inner2 extends Inner1 {
        public Inner2() {
            super();  // invokes Inner1() constructor
        }
    }
}

