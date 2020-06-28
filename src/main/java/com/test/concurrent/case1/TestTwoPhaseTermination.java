package com.test.concurrent.case1;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestTwoPhaseTermination")
public class TestTwoPhaseTermination {

	public static void main(String[] args) throws InterruptedException {
		TwoPhaseTermination tpt = new TwoPhaseTermination();
		tpt.start();
		
		Thread.sleep(3500);
		tpt.stop();
	}

}

@Slf4j(topic = "c.TwoPhaseTermination")
class TwoPhaseTermination{
	
	private Thread monitor;
	
	public void start() {
		monitor = new Thread(()-> {
			while(true) {
				Thread current = Thread.currentThread();
				if(current.isInterrupted()) {
					log.debug("handle something before end");
					break;
				}
				
				try {
					Thread.sleep(1000); //interrupt case1
					log.debug("execute monitor"); //interrupt case2
				} catch (InterruptedException e) {
					e.printStackTrace();
					//reset interrupt tag
					current.interrupt();
				}
			}
		});
		
		monitor.start();
	}
	
	public void stop() {
		monitor.interrupt();
	}
	
}
