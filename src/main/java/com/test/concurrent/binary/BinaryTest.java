package com.test.concurrent.binary;

import java.math.BigDecimal;

public class BinaryTest {

	public static void main(String[] args) {
		floatToDecimal();
	}
	
	private static void floatToDecimal() {
		Float floatNumber = 0.91f;
		System.out.println(floatNumber.toString());
		
		BigDecimal decimalNumber = new BigDecimal(floatNumber);
		System.out.println(decimalNumber);
	}
	

}
