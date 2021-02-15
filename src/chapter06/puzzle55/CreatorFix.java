package chapter06.puzzle55;

public class CreatorFix {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
           // Creature creature = new Creature();
            new Creature();
        }
        System.out.println(Creature.numCreated());
    }
}
