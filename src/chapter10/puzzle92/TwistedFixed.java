package chapter10.puzzle92;

public class TwistedFixed {

    private final String name;

    TwistedFixed(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    private void reproduce() {
        new TwistedFixed("reproduce") {
            void printName() {
                System.out.println(name());
                System.out.println(this.name());
            }
        }.printName();
    }

    public static void main(String[] args) {
        new TwistedFixed("main").reproduce();
    }
}
