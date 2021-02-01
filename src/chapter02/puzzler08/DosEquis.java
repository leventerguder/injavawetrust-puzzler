package chapter02.puzzler08;

public class DosEquis {
    public static void main(String[] args) {

        char x = 'X';
        int i = 0;

        System.out.print(true ? x : 0);
        System.out.print(false ? i : x);

        /*
        If you ran the program, however, you found that it prints X88. This behavior seems strange.
        The first print statement prints X and the second prints 88. What accounts for their different behavior?
         */
    }
}
