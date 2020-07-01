package com.test.concurrent.mythreadpool;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestPool")
public class TestPool {

	public static void main(String[] args) {
		ThreadPool threadPool = new ThreadPool(1, 1000, TimeUnit.MILLISECONDS, 1, (queue, task) -> {
			// 1.死等
//			queue.put(task);
			// 2.带超时等待
//			queue.offer(task, 1500, TimeUnit.MILLISECONDS);
			// 3.放弃
//			log.debug("放弃{}", task);
			// 4.让调用者抛出异常
//          throw new RuntimeException("任务执行失败 " + task);
			// 5.让调用者自己执行任务
			task.run();
		});

		for (int i = 0; i < 4; i++) {
			int j = i;
			threadPool.execute(() -> {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				log.debug("{}", j);
			});
		}

	}

}
