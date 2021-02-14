package chapter06.puzzle49;

import java.util.Calendar;

public class ElvisFixed {

    /*
    To fix a class initialization cycle, reorder the static field initializers so that each initializer
    appears before any initializers that depend on it. In this program,
    the declaration for CURRENT_YEAR belongs before the declaration for INSTANCE,
    because the creation of an Elvis instance requires that CURRENT_YEAR be initialized.
     */
    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    public static final ElvisFixed INSTANCE = new ElvisFixed();

    private final int beltSize;

    private ElvisFixed() {
        beltSize = CURRENT_YEAR - 1930;
    }

    public int beltSize() {
        return beltSize;
    }

    public static void main(String[] args) {
        System.out.println("Elvis wears a size " + INSTANCE.beltSize() + " belt.");
    }

}
