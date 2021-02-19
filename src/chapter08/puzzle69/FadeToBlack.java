package chapter08.puzzle69;

public class FadeToBlack {
    public static void main(String[] args) {

        System.out.println(((X.Y) null).Z);

        System.out.println(X.Y.Z);
    }
}

class X {
    static class Y {
        static String Z = "Black";
    }
    static C Y = new C();
}

class C {
    String Z = "White";
}
