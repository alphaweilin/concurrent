package com.test.concurrent.lock;

import lombok.extern.slf4j.Slf4j;
import com.test.concurrent.util.Sleeper;

@Slf4j(topic = "c.Test")
public class Test {

	public static void main(String[] args) {
		MyLock lock = new MyLock();
		new Thread(()->{
			lock.lock();
			try {
				log.debug("locking......");
				Sleeper.sleep(1);
			} finally {
				log.debug("unlocking.....");
				lock.unlock();
			}
		},"t1").start();
		
		new Thread(()->{
			lock.lock();
			try {
				log.debug("locking......");
			} finally {
				log.debug("unlocking.....");
				lock.unlock();
			}
		},"t2").start();
	}

}
