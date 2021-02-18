package chapter07.puzzle63;

import java.util.*;

public class MoreNames {

    private Map<String, String> m = new HashMap<String, String>();

    public void MoreNames() {
        m.put("Mickey", "Mouse");
        m.put("Mickey", "Mantle");
    }

    public int size() {
        return m.size();
    }

    public static void main(String args[]) {
        MoreNames moreNames = new MoreNames();
        System.out.println(moreNames.size());
    }

    /*
    The problem is that MoreNames has no programmer-declared constructor.
    What it does have is a void-returning instance method called MoreNames,
    which the author probably intended as a constructor.
     */
}

