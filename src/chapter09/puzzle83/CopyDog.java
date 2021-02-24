package chapter09.puzzle83;


import java.io.*;

public class CopyDog {

    public static void main(String[] args) {


        Dog newDog = (Dog) deepCopy(Dog.INSTANCE);
        System.out.println(newDog == Dog.INSTANCE); //false
        System.out.println(newDog);

    }


    // This method is very slow and generally a bad idea!
    public static Object deepCopy(Object obj) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            new ObjectOutputStream(bos).writeObject(obj);
            ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
            return new ObjectInputStream(bin).readObject();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

}
