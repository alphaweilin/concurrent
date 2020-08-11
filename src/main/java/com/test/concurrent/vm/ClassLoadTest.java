package com.test.concurrent.vm;

public class ClassLoadTest {

	public static void main(String[] args) throws Exception {
//		Class<?> aClass = Class.forName("com.test.concurrent.vm.Demo1");
		Class<?> aClass = Class.forName("com.sun.java.accessibility.AccessBridge");
//		Class<?> aClass = Class.forName("java.lang.String");
		System.out.println(aClass.getClassLoader()); 
	}

}
