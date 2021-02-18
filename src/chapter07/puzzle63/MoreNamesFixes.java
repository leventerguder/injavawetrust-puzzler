package chapter07.puzzle63;

import java.util.HashMap;
import java.util.Map;

public class MoreNamesFixes {

    private Map<String, String> m = new HashMap<String, String>();

    public MoreNamesFixes() {
        m.put("Mickey", "Mouse");
        m.put("Mickey", "Mantle");
    }

    public int size() {
        return m.size();
    }

    public static void main(String args[]) {
        MoreNamesFixes moreNames = new MoreNamesFixes();
        System.out.println(moreNames.size());
    }

}

