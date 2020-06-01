package com.test.concurrent.vm;

public class Demo2 {
	private void test() {
		System.out.println("test");
	}

	public static void main(String[] args) {
		Demo2 demo2 = new Demo2();
		demo2.test();
		Animal dog = new Dog();
		Animal cat = new Cat();
		dog.eat();
		dog.toString();
		cat.eat();
		cat.toString();
	}

}

abstract class Animal {
	abstract void eat();
	
	@Override
	public String toString() {
		return "this is " + this.getClass().getSimpleName();
	}

}

class Dog extends Animal {

	@Override
	void eat() {
		System.out.println("dog eats meat");
	}
}

class Cat extends Animal{

	@Override
	void eat() {
		System.out.println("cat eats finish");
	}
	
}
