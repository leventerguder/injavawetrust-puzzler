package chapter10.puzzle90;

public class Outer3 {
    class Inner1 extends Outer {
    }

    class Inner2 extends Inner1 {
        public Inner2() {
            Outer3.this.super();
        }
    }
}
