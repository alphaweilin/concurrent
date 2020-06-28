package com.test.concurrent.case1;

import java.util.concurrent.locks.LockSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestInterrupt")
public class TestInterrupt {
    public static void main(String[] args) throws InterruptedException {
        test2();
    }
    private static void test4() {
        Thread t1 = new Thread(() -> {
   
                log.debug("park...");
                LockSupport.park();
                log.debug("打断状态：{}", Thread.interrupted());
        });
        t1.start();


        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        t1.interrupt();
        LockSupport.unpark(t1);
    }
    private static void test3() {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();


        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        t1.interrupt();
    }
    private static void test2() throws InterruptedException {
        Thread t2 = new Thread(()->{
            while(true) {
                Thread current = Thread.currentThread();
                boolean interrupted = current.isInterrupted();
                if(interrupted) {
                    log.debug(" 打断状态: {}", interrupted);
                    break;
                }
            }
        }, "t2");
        t2.start();

        Thread.sleep(500);
        t2.interrupt();
    }
    private static void test1() throws InterruptedException {
        Thread t1 = new Thread(()->{
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }, "t1");
        t1.start();

        Thread.sleep(500);
        t1.interrupt();
        log.debug(" 打断状态: {}", t1.isInterrupted());
    }
}