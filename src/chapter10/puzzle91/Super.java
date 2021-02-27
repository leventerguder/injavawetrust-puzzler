package chapter10.puzzle91;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

class Super implements Serializable {
    final Set<Super> set = new HashSet<Super>();
}
