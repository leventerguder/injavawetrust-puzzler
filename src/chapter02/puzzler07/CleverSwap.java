package chapter02.puzzler07;

public class CleverSwap {
    public static void main(String[] args) {
        int x = 1984;  // (0x7c0)
        int y = 2001;  // (0x7d1)
        x ^= y ^= x ^= y;
        System.out.println("x = " + x + "; y = " + y);
    }
}
