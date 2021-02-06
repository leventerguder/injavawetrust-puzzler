package chapter04.puzzle25;

public class IncrementFixed {

    public static void main(String[] args) {

        int j = 0;
        for (int i = 0; i < 100; i++)
            //j = j++; // bug!
            j++;
        System.out.println(j);

    }
}
