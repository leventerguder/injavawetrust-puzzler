package _15.string.replace;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSeparator {

	public static void main(String[] args) {
		
		String className = FileSeparator.class.getName();
		
		// File.separator

		// Unix/Linux /
		// Windows \
		// Windows system throws Exception!
		// \ is escape char , it is invalid!
		System.out.println(className.replaceAll(Pattern.quote("."), File.separator));
		
		//It is valid for all systems!
		System.out.println(className.replaceAll(Pattern.quote("."), Matcher.quoteReplacement(File.separator)));
	}
}
