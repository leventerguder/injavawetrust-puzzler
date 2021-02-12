package chapter05.puzzle44;

public class Strange2 {
    public static void main(String[] args) {
        Missing m;
        try {
            m = new Missing();
        } catch (NoClassDefFoundError ex) {
            System.out.println("Got it!");
        }
    } 
}
