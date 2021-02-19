package chapter08.puzzle67;

public class StrungOut {

    public static void main(String[] args) {

        String s = new String("Hello world");
        System.out.println(s);

        /*
        The VM can't find the main method because it isn't there. Although StrungOut has a method named main,
        it has the wrong signature. A main method must accept a single argument that is an array of strings [JVMS 5.2].
        What the VM is struggling to tell us is that StrungOut.main accepts an array of our String class,
        which has nothing whatsoever to do with java.lang.String.
         */
    }
}

/*
Never reuse class names from the package java.lang. The same lesson applies to library designers.
The Java platform designers slipped up a few times. Notable examples include java.sql.Date,
which conflicts with java.util.Date, and org.omg.CORBA.Object.
 */
class String {
    private final java.lang.String s;

    public String(java.lang.String s) {
        this.s = s;
    }

    public java.lang.String toString() {
        return s;
    }
}
