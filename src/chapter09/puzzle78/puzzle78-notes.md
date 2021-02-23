# Puzzle 78: Reflection Infection

This puzzle illustrates a simple application of reflection. What does this program print?

<pre>

import java.util.*;
import java.lang.reflect.*;

public class Reflector {

    public static void main(String[] args) throws Exception {
        Set<String> s = new HashSet<String>();
        s.add("foo");
        Iterator it = s.iterator();
        Method m = it.getClass().getMethod("hasNext");
        System.out.println(m.invoke(it));
    }

}
</pre>
