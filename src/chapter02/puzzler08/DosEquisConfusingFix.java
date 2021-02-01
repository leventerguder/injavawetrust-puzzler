package chapter02.puzzler08;

public class DosEquisConfusingFix {
    public static void main(String[] args) {

        char x = 'X';
        final int i = 0;

        System.out.print(true ? x : 0);
        System.out.print(false ? i : x);

        /*
   Putting the final modifier on the declaration for i would turn i into a constant expression,
   causing the program to print XX, but it would still be confusing.
   To eliminate the confusion, it is best to change the type of i from int to char, avoiding the mixed-type computation.
         */
    }
}
