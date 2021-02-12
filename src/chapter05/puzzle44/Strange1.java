package chapter05.puzzle44;

public class Strange1 {
    public static void main(String[] args) {
        try {
            Missing m = new Missing();
        } catch (NoClassDefFoundError ex) {
            System.out.println("Got it!");
        }
    }
}
