package chapter06.puzzle46;


public class Confusing {
    private Confusing(Object o) {
        System.out.println("Object");
    }

    private Confusing(double[] dArray) {
        System.out.println("double array");
    }

    public static void main(String[] args) {
        new Confusing(null);
    }

    /*
    In our program, both constructors are accessible and applicable.
    The constructor Confusing(Object) accepts any parameter passed to Confusing(double[]),
    so Confusing(Object) is less specific. (Every double array is an Object,
    but not every Object is a double array.) The most specific constructor is therefore Confusing(double[]),
    which explains the program's output.
     */
}
