package chapter03.puzzler12;

public class AbcFixed {
    public static void main(String[] args) {

        String letters = "ABC";
        char[] numbers = {'1', '2', '3'};

        /*
        You can explicitly convert the array to a string before invoking string concatenation:
         */
        System.out.println(letters + " easy as " + String.valueOf(numbers));

        /*

        Alternatively, you can break the System.out.println invocation
        in two to make use of the char[] overloading of println:
         */

        System.out.print(letters + " easy as ");
        System.out.println(numbers);


    }
}
