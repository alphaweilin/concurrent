package com.test.concurrent.case1;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test4")
public class Test4 {

	public static void main(String[] args) throws InterruptedException {
		Room room = new Room();
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 5000; i++) {
				room.increment();
			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 5000; i++) {
				room.decrement();
			}
		});

		t1.start();
		t2.start();
		t1.join();
		t2.join();

		log.debug("counter value: {}", room.getCounter());
	}
}

class Room {
	private int counter = 0;

	public void increment() {
		synchronized (this) {
			counter++;
		}
	}

	public void decrement() {
		synchronized (this) {
			counter--;
		}
	}

	public int getCounter() {
		synchronized (this) {
			return counter;
		}
	}
}