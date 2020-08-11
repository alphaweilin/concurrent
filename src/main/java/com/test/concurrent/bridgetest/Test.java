package com.test.concurrent.bridgetest;

import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) throws Exception {
		Animal animal = new Cat();
		animal.getObject("cat");
		Animal animal2 = new Dog<String>();
		System.out.println(animal.getObject("dog"));
		
		for(Method method : Class.forName("com.test.concurrent.bridgetest.Dog").getDeclaredMethods()) {
			System.out.println(method.getName());
		}
	}

}
