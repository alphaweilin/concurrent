package com.test.concurrent.binary;

import java.math.BigDecimal;

public class BinaryTest {

	public static void main(String[] args) {
		floatToDecimal();
	}
	
	private static void floatToDecimal() {
		Float floatNumber = 0.91f;
//		Float floatNumber = 0.5f;
		System.out.println(floatNumber);
		
		BigDecimal decimalNumber = new BigDecimal(floatNumber);
		System.out.println(decimalNumber);
	}
	

}
