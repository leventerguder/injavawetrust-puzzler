package chapter04.puzzle30;

public class OperatorOverloading {

    public static void main(String[] args) {

        String i = "Java!";

        while (i != i + 0) {
            System.out.println(" + operator overloading... infinite loop");
        }
    }
}
