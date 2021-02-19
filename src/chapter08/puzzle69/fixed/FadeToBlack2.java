package chapter08.puzzle69.fixed;

public class FadeToBlack2 {

    /*
     you can also solve the puzzle by using X.Y in the extends clause of a type variable declaration:
     */
    public static <T extends X.Y> void main(String[] args) {
        System.out.println(T.Z);
    }
}
