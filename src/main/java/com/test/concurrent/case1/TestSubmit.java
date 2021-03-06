package com.test.concurrent.case1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestSubmit")
public class TestSubmit {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService pool = Executors.newFixedThreadPool(2);

		List<Future<String>> futures = pool.invokeAll(Arrays.asList(() -> {
			log.debug("begin");
			Thread.sleep(1000);
			return "1";
		}, () -> {
			log.debug("begin");
			Thread.sleep(500);
			return "2";
		}, () -> {
			log.debug("begin");
			Thread.sleep(2000);
			return "3";
		}));
		
		futures.forEach(f->{
			try {
				log.debug("{}",f.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

}
