package com.test.concurrent.mythreadpool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.BlockingQueue")
class BlockingQueue<T> {
	// 1.任务队列
	private Deque<T> queue = new ArrayDeque();

	// 2.锁
	private ReentrantLock lock = new ReentrantLock();

	// 3.生产者条件变量
	private Condition fullWaitSet = lock.newCondition();

	// 4.消费者条件变量
	private Condition emptyWaitSet = lock.newCondition();

	// 5.容量
	private int capacity;

	public BlockingQueue(int capacity) {
		super();
		this.capacity = capacity;
	}

	// 带超时的阻塞获取
	public T poll(long timeout, TimeUnit unit) {
		lock.lock();
		try {
			// 讲timeout统一转化为纳秒
			long nanos = unit.toNanos(timeout);
			while (queue.isEmpty()) {
				try {
					// 如果剩余时间小于等于0，不再等待，直接退出
					if (nanos <= 0) {
						return null;
					}
					// 返回的是剩余时间，如果虚假唤醒，下次等待只会等待剩余时间
					nanos = emptyWaitSet.awaitNanos(nanos);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			T t = queue.removeFirst();
			fullWaitSet.signal();
			return t;
		} finally {
			lock.unlock();
		}
	}

	// 阻塞获取
	public T take() {
		lock.lock();
		try {
			while (queue.isEmpty()) {
				try {
					emptyWaitSet.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			T t = queue.removeFirst();
			fullWaitSet.signal();
			return t;
		} finally {
			lock.unlock();
		}
	}

	// 带超时时间阻塞添加
	public boolean offer(T element, long timeout, TimeUnit timeUnit) {
		lock.lock();
		try {
			long nanos = timeUnit.toNanos(timeout);
			while (queue.size() == capacity) {
				try {
					if (nanos <= 0) {
						log.debug("等待超时，放弃加入任务队列....{}", element);
						return false;
					}
					log.debug("等待加入任务队列....{}", element);
					nanos = fullWaitSet.awaitNanos(nanos);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			log.debug("加入任务队列{}", element);
			queue.addLast(element);
			emptyWaitSet.signal();
			return true;
		} finally {
			lock.unlock();
		}

	}

	// 阻塞添加
	public void put(T element) {
		lock.lock();
		try {
			while (queue.size() == capacity) {
				try {
					log.debug("等待加入任务队列....{}", element);
					fullWaitSet.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			log.debug("加入任务队列{}", element);
			queue.addLast(element);
			emptyWaitSet.signal();
		} finally {
			lock.unlock();
		}

	}

	public void tryPut(RejectPolicy<T> rejectPolicy, T element) {
		lock.lock();
		try {
			if (queue.size() == capacity) { // 队列已满
				rejectPolicy.reject(this, element);
			} else { // 队列未满
				log.debug("加入任务队列{}", element);
				queue.addLast(element);
				emptyWaitSet.signal();
			}

		} finally {
			lock.unlock();
		}
	}

	// 获取大小
	public int size() {
		lock.lock();

		try {
			return queue.size();
		} finally {
			lock.unlock();
		}
	}

}