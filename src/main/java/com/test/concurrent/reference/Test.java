package com.test.concurrent.reference;

import java.util.ArrayList;
import java.util.List;

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
	
	public static void testForList() {
		List<User> list = new ArrayList<User>();
		User user = new User("tom", "18");
		for (int i = 0; i < 10; i++) {
			user = new User("tom"+i, "age"+i);
//			user.setName("tom"+i);
//			user.setAge("age"+i);
			list.add(user);
		}
		list.forEach(System.out::println);
	}

}
