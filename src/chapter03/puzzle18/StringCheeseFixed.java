package chapter03.puzzle18;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class StringCheeseFixed {

    public static void main(String args[]) throws UnsupportedEncodingException {
        byte bytes[] = new byte[256];
        for (int i = 0; i < 256; i++)
            bytes[i] = (byte) i;
        String str = new String(bytes, StandardCharsets.ISO_8859_1);
        for (int i = 0, n = str.length(); i < n; i++)
            System.out.print((int) str.charAt(i) + " ");

    }
}
