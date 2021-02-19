package chapter08.puzzle68;

public class ShadesOfGray {

    public static void main(String[] args) {
        System.out.println(X.Y.Z);
    }
}


class X {
    static class Y {
        static String Z = "Black";
    }
    /*
    When a variable and a type have the same name and both are in scope, the variable name takes precedence
     */
    static C Y = new C();
}

class C {
    String Z = "White";
}
