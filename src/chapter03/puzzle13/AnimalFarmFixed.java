package chapter03.puzzle13;

public class AnimalFarmFixed {

    public static void main(String[] args) {
        /*
        When comparing object references, you should use the equals method in preference
        to the == operator unless you need to compare object identity rather than value.
         */

        final String pig = "length: 10";
        final String dog = "length: " + pig.length();

        System.out.println("Animals are equal: " + pig.equals(dog));
    }
}
