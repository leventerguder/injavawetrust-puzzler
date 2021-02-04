package chapter03.puzzle18;

public class StringCheese {
    public static void main(String args[]) {
        byte bytes[] = new byte[256];
        for (int i = 0; i < 256; i++)
            bytes[i] = (byte) i;
        String str = new String(bytes);
        for (int i = 0, n = str.length(); i < n; i++)
            System.out.print((int) str.charAt(i) + " ");
    }
}
