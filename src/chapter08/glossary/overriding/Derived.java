package chapter08.glossary.overriding;

class Base {
    public void f() {
    }
}

class Derived extends Base {
    public void f() {
    } // overrrides Base.f()
}
