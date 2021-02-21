package chapter08.glossary.hiding;

class Base {
    public static void f() {
    }
}

class Derived extends Base {
    public static void f() {
    } // hides Base.f()
}
