package chapter05.puzzle36;

public class IndecisiveThrowException {

    public static void main(String[] args) {
        System.out.println(decision());
    }


    static boolean decision() {
        try {
            throw new Exception();
        } finally {
            return false;
        }
    }
}
