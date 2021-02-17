# Puzzle 61: The Dating Game

The following program exercises some basic features of the Date and Calendar classes. What does it print?


<pre>
import java.util.*;

public class DatingGame {
    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        cal.set(1999, 12, 31); // Year, Month, Day
        System.out.print(cal.get(Calendar.YEAR) + " ");
        Date d = cal.getTime();
        System.out.println(d.getDay());

    }
}

</pre>

It seems that the program should print 1999 31, but it doesn't; it prints 2000 1.

No, it's something much worse: It is the dreaded Date/Calendar problem. When the Java platform was first released, 
its only support for calendar calculations was the Date class. This class was limited in power, 
especially when it came to support for internationalization, and it had a basic design flaw: 
Date instances were mutable. In release 1.1, the Calendar class was added to the platform to rectify the 
shortcomings of Date; most Date methods were deprecated. Unfortunately, 
this only made a bad situation worse. Our program illustrates a few of the problems with the Date and Calendar APIs.


Unfortunately, Date represents January as 0, and Calendar perpetuates this mistake.
ut the standard (Gregorian) calendar has only 12 months; surely this method invocation should cause an 
IllegalArgumentException? It should, but it doesn't. The Calendar class silently substitutes the 
first month of the next year, in this case, 2000. This explains the first number printed by our program (2000).

To find out, you have to read the documentation, which says that Date.getDay returns 
the day of the week represented by the Date instance, not the day of the month.
The returned value is 0-based, starting at Sunday, 
so the 1 printed by the program indicates that January 31, 2000, fell on a Monday. 


<pre>
import java.util.*;

public class DatingGameFixed {

    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        cal.set(1999, Calendar.DECEMBER, 31);
        System.out.print(cal.get(Calendar.YEAR) + " ");
        System.out.println(cal.get(Calendar.DAY_OF_MONTH));

    }

}

</pre>

Be careful when using Calendar or Date; always consult the API documentation.
