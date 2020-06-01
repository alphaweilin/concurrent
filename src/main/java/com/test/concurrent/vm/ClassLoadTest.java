package com.test.concurrent.vm;

public class ClassLoadTest {

	public static void main(String[] args) throws Exception {
		Class<?> aClass = Class.forName("com.test.concurrent.vm.Demo1");
		System.out.println(aClass.getClassLoader()); 
	}

}
