package com.test.concurrent.case1;

public class Test {
	public static void main(String[] args) {
		int i = 6;
		i = i++;
		System.out.println(i);
	}
}