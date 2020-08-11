package com.test.concurrent.myasync;

public class Task extends Thread{
	private Runnable runnable;
	private Handler handler;
	
	public Task(Runnable runnable, Handler handler) {
		this.runnable = runnable;
		this.handler = handler;
	}
	
	@Override
	public void run() {
		try {
			runnable.run();
			handler.onSuccess();
		} catch (Exception e) {
//			e.printStackTrace();
			handler.onFailure();
		}
		
	}

}
