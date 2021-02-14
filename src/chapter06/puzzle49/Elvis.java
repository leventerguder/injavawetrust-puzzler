package chapter06.puzzle49;

import java.util.Calendar;

public class Elvis {

    public static final Elvis INSTANCE = new Elvis();

    private final int beltSize;
    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    private Elvis() {
        beltSize = CURRENT_YEAR - 1930;
    }

    public int beltSize() {
        return beltSize;
    }

    public static void main(String[] args) {
        System.out.println("Elvis wears a size " + INSTANCE.beltSize() + " belt.");
    }

    /*
    Next, static field initializers are executed in order of appearance.
    The first static field is INSTANCE. Its value is computed by invoking the Elvis() constructor.
     */

}
