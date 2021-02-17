package chapter07.puzzle60;

public class HasMoreBits {

    public static void main(String[] args) {

        // puzzle D

        int i = 8; // 1000
        int j = 7; // 0111

        System.out.println(hasMoreBitsSet(i, j));


        int x = 9; // 1001
        int y = 4; // 0100

        System.out.println(hasMoreBitsSet(x, y));

    }

    static boolean hasMoreBitsSet(int i, int j) {
        return (Integer.bitCount(i) > Integer.bitCount(j));
    }

}
