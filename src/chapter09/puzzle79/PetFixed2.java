package chapter09.puzzle79;

public class PetFixed2 {

    public final String name;
    public final String food;
    public final String sound;

    public PetFixed2(String name, String food, String sound) {
        this.name = name;
        this.food = food;
        this.sound = sound;
    }

    public void eat() {
        System.out.println(name + ": Mmmmm, " + food);
    }

    public void play() {
        System.out.println(name + ": " + sound + " " + sound);
    }

    public void sleep() {
        System.out.println(name + ": Zzzzzzz...");
    }

    public void live() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    eat();
                    play();
                    sleep();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new PetFixed2("Fido", "beef", "Woof").live();
    }
}
