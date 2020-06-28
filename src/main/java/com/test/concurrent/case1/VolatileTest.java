package com.test.concurrent.case1;

public class VolatileTest {

	volatile static boolean run = true;
//	static boolean run = true;

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(() -> {
			while (run) {
//				System.out.println("123");
			}
		});
		t.start();

		Thread.sleep(1000);
		run = false; // 线程t不会如预想的停下来
	}

}
