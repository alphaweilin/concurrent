package com.test.concurrent.vm;

public class InitTest {
	static {
		int a=10;
		System.out.println("cinit");
	}
	
	{
		String bString="test";
		System.out.println("init");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new InitTest();
	}

}
