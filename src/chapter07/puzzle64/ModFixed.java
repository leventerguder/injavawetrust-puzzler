package chapter07.puzzle64;

public class ModFixed {

    public static void main(String[] args) {

        final int MODULUS = 3;
        int[] histogram = new int[MODULUS];
        int i = Integer.MIN_VALUE;
        do {
            histogram[mod(i, MODULUS)]++;
        } while (i++ != Integer.MAX_VALUE);

        for (int j = 0; j < MODULUS; j++)
            System.out.println(histogram[j] + " ");

         /*
        1431655765
        1431655766
        1431655765
         */
    }

    private static int mod(int i, int modulus) {
        int result = i % modulus;
        return result < 0 ? result + modulus : result;
    }

}
