package chapter04.puzzle33;

public class LooperMeetsTheWolfman {

    public static void main(String[] args) {

        int i = Integer.MIN_VALUE;

        while (i != 0 && i == -i) {
            System.out.println("infinite loop ?");
        }
    }
}
