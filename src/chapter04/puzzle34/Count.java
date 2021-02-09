package chapter04.puzzle34;

public class Count {

    public static void main(String[] args) {

        final int START = 2000000000;

        int counter = 0;
        for (float f = START; f < START + 50; f++)
            counter++;
        System.out.println(counter);
    }
}
