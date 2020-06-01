package com.test.concurrent.case1;

import java.util.ArrayList;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestThreadSafe")
public class TestThreadSafe {
	static final int THREAD_NUMBER = 2;
	static final int LOOP_NUMBER = 200;

	public static void main(String[] args) {
		ThreadSafe test = new ThreadSafe();
		for (int i = 0; i < THREAD_NUMBER; i++) {
			new Thread(() -> {
				test.method1(LOOP_NUMBER);
			}, "Thread" + (i + 1)).start();
		}

	}

}

class ThreadUnsafe {
	ArrayList<String> list = new ArrayList<String>();

	public void method1(int loopNumber) {
		for (int i = 0; i < loopNumber; i++) {
			method2();
			method3();
		}
	}

	private void method2() {
		list.add("1");
	}

	private void method3() {
		list.remove(0);
	}
}

class ThreadSafe {

	public void method1(int loopNumber) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < loopNumber; i++) {
			method2(list);
			method3(list);
		}
	}

	public void method2(ArrayList<String> list) {
		list.add("1");
	}

	public void method3(ArrayList<String> list) {
		list.remove(0);
	}
}

