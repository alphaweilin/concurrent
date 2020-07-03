package com.test.concurrent.mythreadpool;

public class MyStrategyPattern {

	public static void main(String[] args) {
		test((item)->{System.out.println(item);}, "hello world");

	}
	
	private static void test(Handler<String> handler, String str) {
		handler.handle(str);
	}

}

@FunctionalInterface
interface Handler<T>{
	void handle(T item);
}
