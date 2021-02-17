package chapter07.puzzle61;

import java.time.LocalDate;
import java.time.Month;

public class LocalDateExample {

    public static void main(String[] args) {

        LocalDate localDate = LocalDate.of(1999, Month.DECEMBER, 31);

        System.out.print(localDate.getYear() + " ");
        System.out.println(localDate.getDayOfMonth());

    }
}
