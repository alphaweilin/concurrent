package com.test.concurrent.case1;

import java.util.concurrent.FutureTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestCreateThread")
public class TestCreateThread {

	public static void main(String[] args) throws Exception {
		//create by thread directly
		Thread t1 = new Thread("t1") {
			@Override
			public void run() {
				log.debug("this is t1");
			}
		};
		t1.start();
		
		
		//create by runnable
		Thread t2 = new Thread(()->log.debug("this is t2"),"t2");
		t2.start();
		
		//create by futuretask, futureTask is created by callable
		FutureTask<Integer> futureTask = new FutureTask<>(() -> {
			log.debug("this is t3");
			return 100;
		});
		new Thread(futureTask,"t3").start();
		log.debug("{}",futureTask.get());
		
		Runnable t4 = ()->{log.debug("test");};
		t4.run();
		

	}

}
