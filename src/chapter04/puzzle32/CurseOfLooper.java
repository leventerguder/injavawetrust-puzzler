package chapter04.puzzle32;

public class CurseOfLooper {

    public static void main(String[] args) {

        Integer i = new Integer(0);
        Integer j = new Integer(0);

        while (i <= j && j <= i && i != j) {
            System.out.println("infinite loop.");
        }

    }
}
