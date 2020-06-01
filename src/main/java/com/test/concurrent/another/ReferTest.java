package com.test.concurrent.another;

public class ReferTest {
	public static void main(String[] args) {
//		test1();
		test2();
	}
	
	static void test1(){
		System.out.println(A.node);
		System.out.println(A.node.node);
		System.out.println(A.node.name);
		A node1 = new A();
		System.out.println(node1.node);
	}
	
	static void test2() {
		B node1 = new B();
		node1.node = node1;
	}

}

class A{
	public String name = "testA";
	public static A node = new A();
}

class B{
	public String name = "testA";
	public B node;
	
}
