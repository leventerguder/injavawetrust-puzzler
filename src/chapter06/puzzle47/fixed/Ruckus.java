package chapter06.puzzle47.fixed;

public class Ruckus {

    public static void main(String[] args) {

        Dog dogs[] = {new Dog(), new Dog()};

        for (int i = 0; i < dogs.length; i++)
            dogs[i].woof();

        Cat cats[] = {new Cat(), new Cat(), new Cat()};
        for (int i = 0; i < cats.length; i++)
            cats[i].meow();

        System.out.print(Dog.woofCount() + " woofs and ");
        System.out.println(Cat.meowCount() + " meows");

    }
}

