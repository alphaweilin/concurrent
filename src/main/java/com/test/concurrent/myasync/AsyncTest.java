package com.test.concurrent.myasync;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.AsyncTest")
public class AsyncTest {
	
	public static void main(String[] args) {
		
		Runnable runnable = ()->{
			log.info("start IO operation ");
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info("finish IO operation");
		};
		
		Runnable runnable1 = ()->{
			log.info("start IO operation ");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			throw new RuntimeException();
//			log.info("finish IO operation");
		};
		
		Handler handler = new Handler() {
			
			@Override
			public void onSuccess() {
				log.info("success");
				
			}
			
			@Override
			public void onFailure() {
				log.info("failture");
				
			}
		};
		
		Task task = new Task(runnable, handler);
		task.start();
		
		for (int i = 0; i < 10; i++) {
			log.info("run tasks{} on main thread",i);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
