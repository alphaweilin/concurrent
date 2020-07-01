package com.test.concurrent.mythreadpool;


@FunctionalInterface
public interface RejectPolicy<T> {
	void reject(BlockingQueue<T> queue, T task);
}
