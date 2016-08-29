package _15.string.replace;

import java.io.File;

public class Replace {
	public static void main(String[] args) {
		String className = FileSeparator.class.getName();
		
		//UNIX systems /
		//Microsoft Windows systems '\\'
		System.out.println(className.replace('.',File.separatorChar));
	}
}
