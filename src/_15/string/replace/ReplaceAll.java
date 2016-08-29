package _15.string.replace;

import java.util.regex.Pattern;

public class ReplaceAll {

	public static void main(String[] args) {
		
		String className = ReplaceAll.class.getName();
		
		System.out.println(className);
		System.out.println(className.replaceAll(".", "/")); //Meta character
		System.out.println(className.replaceAll("\\.", "/")); // the dot character
		System.out.println(className.replaceAll(Pattern.quote("."), "/"));
						
	}
}
