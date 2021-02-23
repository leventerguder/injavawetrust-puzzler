package chapter09.puzzle78;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ReflectorFixed {

    public static void main(String[] args) throws Exception {
        Set<String> s = new HashSet<String>();
        s.add("foo");
        Iterator it = s.iterator();
        Method m = Iterator.class.getMethod("hasNext");
        System.out.println(m.invoke(it));
    }

}
