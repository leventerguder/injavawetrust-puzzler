package chapter07.puzzle56;

import java.math.BigInteger;

public class BigProblemFixed {
    public static void main(String[] args) {

        BigInteger fiveThousand = new BigInteger("5000");
        BigInteger fiftyThousand = new BigInteger("50000");
        BigInteger fiveHundredThousand
                = new BigInteger("500000");

        BigInteger total = BigInteger.ZERO;
        total = total.add(fiveThousand);
        total = total.add(fiftyThousand);
        total = total.add(fiveHundredThousand);

        System.out.println(total); // 0

    }
}
