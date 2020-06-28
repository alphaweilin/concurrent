package com.test.concurrent.reference;

public class Test {

	public static void main(String[] args) {
		User user = new User("tom","18");
		System.out.println(user);
		method(user);
		System.out.println(user);
	}
	
	public static void method(User arg) {
		arg = new User("cat", "20");
		System.out.println(arg);
	}

}
