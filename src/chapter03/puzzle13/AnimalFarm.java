package chapter03.puzzle13;

public class AnimalFarm {

    public static void main(String[] args) {

        /*
        If initialized with constant expressions, both pig and dog would indeed refer to the same object,
        but dog is not initialized with a constant expression.
         */
        final String pig = "length: 10";
        final String dog = "length: " + pig.length();


        System.out.println("Animals are equal: " + pig == dog);

        /*
        The + operator, whether used for addition or string concatenation,
         binds more tightly than the == operator. Therefore, the parameter of the println method is evaluated like this:
         */
        System.out.println("Animals are equal: " + (pig == dog));

        System.out.println("Animals are equal: " + pig.equals(dog));


    }

}
