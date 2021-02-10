package chapter05.puzzle36;

public class IndecisiveExit {
    public static void main(String[] args) {
        System.out.println(decision());
    }

    static boolean decision() {
        try {
            System.exit(0); //shut down !
            System.out.println("After exit!");
        } finally {
            return false;
        }
    }
}
