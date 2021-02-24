package chapter09.puzzle83;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CopyDogFixed {

    public static void main(String[] args) {


        DogFixed newDog = (DogFixed) deepCopy(DogFixed.INSTANCE);
        System.out.println(newDog == DogFixed.INSTANCE); //true
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
