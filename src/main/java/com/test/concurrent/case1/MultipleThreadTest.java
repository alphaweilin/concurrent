package com.test.concurrent.case1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.MultipleThreadTest")
public class MultipleThreadTest {
	private static int num = 10;
	private static AtomicInteger atomicNum = new AtomicInteger(0);
	private static CountDownLatch cdl = new CountDownLatch(1000);
	private static CountDownLatch cdl2 = new CountDownLatch(1000);

	public static void main(String[] args) {
		test1();
		test2();
	}

	public static void test1() {

		for (int i = 0; i < 1000; i++) {
			new Thread(() -> {
				for (int j = 0; j < 1000; j++) {
					num++;
				}
				cdl.countDown();
			}, "t" + 1).start();
		}

		try {
			cdl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info(num + "");
	}

	public static void test2() {
		for (int i = 0; i < 1000; i++) {
			new Thread(() -> {
				for (int j = 0; j < 1000; j++) {
					atomicNum.getAndIncrement();
				}
				cdl2.countDown();
			}, "t" + i).start();
		}

		try {
			cdl2.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log.info(atomicNum.get() + "");
	}

}
