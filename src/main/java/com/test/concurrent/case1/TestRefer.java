package com.test.concurrent.case1;

import java.util.ArrayList;
import java.util.List;

public class TestRefer {

	public static void main(String[] args) {
		List<User> list = new ArrayList<>();
		add(list);
		list.forEach(System.out::println);

	}
	
	public static void add(List<User> list) {
		list = new ArrayList<User>();  //list will refer other object on heap
		list.add(new User("tom","12"));
	}

}

class User{
	String name;
	String age;
	
	public User(String name, String age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
	
}
