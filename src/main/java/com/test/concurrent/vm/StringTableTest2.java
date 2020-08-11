package com.test.concurrent.vm;

public class StringTableTest2 {

	public static void main(String[] args) {
		String s = new String("a")+new String("b");
		//注意交换String x="ab"; 和 s.intern();顺序执行结果就会不一样
		//哪行先执行串池里就会存储对应的对象
		String x="ab";
		s.intern();
		System.out.println(s==x);
		
	}

}
