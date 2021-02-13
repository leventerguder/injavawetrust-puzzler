package chapter06.puzzle46;


public class Confusing2 {
    private Confusing2(Object o) {
        System.out.println("Object");
    }

    private Confusing2(double[] dArray) {
        System.out.println("double array");
    }

    public static void main(String[] args) {
        /*
    To invoke the Confusing(Object) constructor with a null parameter, write new Confusing((Object)null).
    This ensures that only Confusing(Object) is applicable.
         */
        new Confusing2((Object)null);
    }
}
