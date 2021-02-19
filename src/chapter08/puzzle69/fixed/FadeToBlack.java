package chapter08.puzzle69.fixed;

public class FadeToBlack {

    static class Xy extends X.Y {
    }

    public static void main(String[] args) {
        System.out.println(Xy.Z);
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
