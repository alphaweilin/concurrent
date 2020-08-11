package com.test.concurrent.vm;

public class SingletonTest {

	public static void main(String[] args) {
//		MySingleton.test();
		MySingleton.getInstance();
	}

}

class MySingleton{
	private MySingleton() {}
	
	public static void test() {
		System.out.println("singleton test");
	}
	
	private static class LazyHolder{
		private static final MySingleton SINGLETON=new MySingleton();
		static {
			System.out.println("LazyHolder cinit");
		}
	}
	
	public static MySingleton getInstance() {
		return LazyHolder.SINGLETON;
	}
}
