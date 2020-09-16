package com.test.concurrent.case1;

public class BinaryNum {

	public static void main(String[] args) {
		//打印的是补码，对于整数或0，补码和原码一致
		//对于负数，原码+1，除符号位外取反，得到补码
		System.out.println(Integer.toBinaryString(-128));  //11111111111111111111111110000000
//      -128		
//		11111111111111111111111110000000 补码
//		11111111111111111111111101111111 反码
//		10000000000000000000000010000000 原码

	}

}
