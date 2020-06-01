package com.test.concurrent.another;

public class ExecptionTest {


	public static void main(String args[]) {
		int i = 0;
		String greetings[] = { " Hello world !", " Hello World !! ",
				" HELLO WORLD !!!" };
		while (i < 4) {
			try {
				//i的设计，避免造成无限循环
				System.out.println(greetings[i]);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("数组下标越界异常");
			} finally {
				System.out.println("--------------------------");
			}
		}
	}


}
