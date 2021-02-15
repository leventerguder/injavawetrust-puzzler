package chapter06.puzzle53;

public class MyThing extends Thing {

    private final int arg;

    public MyThing() {
        this(SomeOtherClass.func());
    }

    private MyThing(int i) {
        super(i);
        arg = i;
    }

}
