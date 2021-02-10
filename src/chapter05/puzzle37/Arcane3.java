package chapter05.puzzle37;


interface Type1 {
    void f() throws CloneNotSupportedException;
}

interface Type2 {
    void f() throws InterruptedException;
}


interface Type3 extends Type1, Type2 {
}

public class Arcane3 implements Type3 {

    @Override
    public void f() {
        System.out.println("Hello world");
    }

    /*
    The flaw in this analysis is the assumption that Type3.
    f can throw either the exception declared on Type1.f or the one declared on Type2.f.
    This simply isn't true. Each interface limits the set of checked exceptions that method f can throw.
     */

    public static void main(String[] args) {
        Type3 t3 = new Arcane3();
        t3.f();
    }

}
