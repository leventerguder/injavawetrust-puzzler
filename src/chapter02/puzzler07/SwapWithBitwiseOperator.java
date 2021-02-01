package chapter02.puzzler07;

public class SwapWithBitwiseOperator {
    public static void main(String[] args) {

        int x = 13;
        int y = 7;

        System.out.println(Integer.toBinaryString(x)); // 1101 // binary
        System.out.println(Integer.toBinaryString(y)); // 111 //binary

        System.out.println("x:" + x + " y:" + y);

		/*
		Even back in those days, this technique was seldom justified.
		Now that CPUs have many registers, it is never justified.
		Like most "clever" code, it is far less clear than its naive counterpart and far slower.
		 */

        // Swaps variables without a temporary - Don't do this!
        x = x ^ y;
        y = y ^ x;
        x = x ^ y;

        System.out.println("After Swap");
        System.out.println("x : " + x);
        System.out.println("y : " + y);

//		x=x^y
//
//		1101
//		0111  
//		------
//		1010  

//		y=y^x
//
//		1010
//		0111
//		-----
//		1101


//		x=x^y
//		1010
//		1101
//		-----
//		0111

    }
}
