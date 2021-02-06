package chapter04.puzzle24;

public class BigDelight3 {

    public static void main(String[] args) {

        for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
            /*
            You can cast the int to a byte, after which you will be comparing one byte value to another:
             */
            if (b == (byte) 0x90)
                System.out.print("Joy!");

        }

        for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
            /*
           Alternatively, you can convert the byte to an int, suppressing sign extension with a mask,
           after which you will be comparing one int value to another:
             */
            if ((b & 0xff) == 0x90)
                System.out.print("Joy!");

        }


    }


}
