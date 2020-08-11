package com.test.concurrent.vm;

//StringTable [ "a", "b" ,"ab" ]  hashtable 结构，不能扩容
public class StringTableTest {
	// 常量池中的信息，都会被加载到运行时常量池中， 这时 a b ab 都是常量池中的符号，还没有变为 java 字符串对象
	// ldc #2 会把 a 符号变为 "a" 字符串对象
	// ldc #3 会把 b 符号变为 "b" 字符串对象
	// ldc #4 会把 ab 符号变为 "ab" 字符串对象

	public static void main(String[] args) {
		String s1 = "a"; // 懒惰的
		String s2 = "b";
		String s3 = "ab";
		String s4 = s1 + s2;
		String s5 = "ab";

		System.out.println(s3 == s4);
		//把s4放入串池，如果有则不放入，如果没有则放入，返回串池中的对象
		s4 = s4.intern();
		System.out.println(s3 == s4);

		System.out.println(s3 == s5);
	}

}
