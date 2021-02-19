# Puzzle 67: All Strung Out

<pre>
public class StrungOut {

    public static void main(String[] args) {

        String s = new String("Hello world");
        System.out.println(s);

    }
}

class String {
    private final java.lang.String s;

    public String(java.lang.String s) {
        this.s = s;
    }

    public java.lang.String toString() {
        return s;
    }
}
</pre>

The class String in the unnamed package is simply a wrapper for a java.lang.String instance. 
It seems the program should print Hello world. If you tried to run the program, though, you found that you could not. 
The VM emits an error message something like this:

Exception in thread "main" java.lang.NoSuchMethodError: main


The VM can't find the main method because it isn't there. Although StrungOut has a method named main, 
it has the wrong signature. A main method must accept a single argument that is an array of strings [JVMS 5.2]. 
What the VM is struggling to tell us is that StrungOut.main accepts an array of our String class, 
which has nothing whatsoever to do with java.lang.String.


If you really must write your own string class, for heaven's sake, don't call itString. 
Avoid reusing the names of platform classes, and never reuse class names from java.lang, 
because these names are automatically imported everywhere. 

<pre>
public class StrungOut {

    public static void main(String[] args) {
        MyString s = new MyString("Hello world");
        System.out.println(s);
    }

}

class MyString {
    private final String s;

    public MyString(String s) {
        this.s = s;
    }

    public String toString() {
        return s;
    }
}

</pre>


Never reuse class names from the package java.lang. The same lesson applies to library designers. 
The Java platform designers slipped up a few times. Notable examples include java.sql.Date, 
which conflicts with java.util.Date, and org.omg.CORBA.Object.
