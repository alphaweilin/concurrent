package com.test.concurrent.mythreadpool;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.ThreadPool")
class ThreadPool {
	// 任务队列
	private BlockingQueue<Runnable> taskQueue;

	// 线程集合
	private HashSet<Worker> workers = new HashSet<>();

	// 核心线程数
	private int coreSize;

	// 获取任务超时时间
	private long timeout;

	private TimeUnit timeUnit;
	
	private RejectPolicy<Runnable> rejectPolicy;

	public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity, RejectPolicy<Runnable> rejectPolicy) {
		this.coreSize = coreSize;
		this.timeout = timeout;
		this.timeUnit = timeUnit;
		this.taskQueue = new BlockingQueue(queueCapacity);
		this.rejectPolicy = rejectPolicy;
	}

	// 执行任务
	public void execute(Runnable task) {
		synchronized (workers) {
			if (workers.size() < coreSize) {
				Worker worker = new Worker(task);
				log.debug("新增 worker{},{}", worker,task);
				workers.add(worker);
				worker.start();
			} else {
//				taskQueue.put(task);
                // 1) 死等
                // 2) 带超时等待
                // 3) 让调用者放弃任务执行
                // 4) 让调用者抛出异常
                // 5) 让调用者自己执行任务
				taskQueue.tryPut(rejectPolicy, task);
			}
		}
	}

	class Worker extends Thread {
		private Runnable task;

		public Worker(Runnable task) {
			this.task = task;
		}

		@Override
		public void run() {
			// 执行任务
			// 1.当task不为空，执行任务
			// 2.当task执行完毕，从任务队列获取任务执行
//			while (null != task || (task = taskQueue.take()) != null) {
			while (null != task || (task = taskQueue.poll(timeout, timeUnit)) != null) {
				try {
					log.debug("正在执行...{}",task);
					task.run();
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					// 执行完毕将task置空
					task = null;
				}
			}

			// 所有任务都执行完毕，将线程从线程池移除
			synchronized (workers) {
				log.debug("worker被移除{}",this);
				workers.remove(this);
			}

		}
	}
}
