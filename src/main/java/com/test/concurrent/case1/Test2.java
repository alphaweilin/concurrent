package com.test.concurrent.case1;

import java.util.concurrent.FutureTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test2")
public class Test2 {
	public static void main(String[] args) throws Exception {
		FutureTask<Integer> task3 = new FutureTask<>(() -> {
			log.debug("hello");
			return 100;
		});
		
		new Thread(task3, "t3").start();
		Integer result = task3.get();
		log.debug("result is: {}",  result);
	}
}
