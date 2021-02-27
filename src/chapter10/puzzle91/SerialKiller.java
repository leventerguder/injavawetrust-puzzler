package chapter10.puzzle91;

import java.io.*;

public class SerialKiller {

    public static void main(String[] args) {

        Sub sub = new Sub(666);
        sub.checkInvariant();
        Sub copy = (Sub) deepCopy(sub);
        copy.checkInvariant();
    }


    // Copies its argument via serialization (See Puzzle 83)
    static public Object deepCopy(Object obj) {
        try {
            ByteArrayOutputStream bos =
                    new ByteArrayOutputStream();
            new ObjectOutputStream(bos).writeObject(obj);
            ByteArrayInputStream bin =
                    new ByteArrayInputStream(bos.toByteArray());
            return new ObjectInputStream(bin).readObject();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

}
