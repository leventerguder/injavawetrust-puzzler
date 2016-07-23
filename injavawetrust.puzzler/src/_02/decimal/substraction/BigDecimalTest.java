package _02.decimal.substraction;

import java.math.BigDecimal;

public class BigDecimalTest {
	public static void main(String[] args) {

		System.out.println(new BigDecimal(1.1));
		System.out.println(new BigDecimal(2.00).subtract(new BigDecimal(1.10)));
		System.out.println(new BigDecimal("2.00").subtract(new BigDecimal("1.10")));

	}
}
