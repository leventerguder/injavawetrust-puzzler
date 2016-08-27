package _14.default$.charset;

import java.io.UnsupportedEncodingException;

public class WithCharsetName {
	public static void main(String args[]) throws UnsupportedEncodingException {
		byte bytes[] = new byte[256];
		for (int i = 0; i < 256; i++)
			bytes[i] = (byte) i;
		String str = new String(bytes,"ISO-8859-9");
		for (int i = 0, n = str.length(); i < n; i++)
			System.out.print((int) str.charAt(i) + " ");

	}
}
