package com.test.concurrent.funcinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {
	
	public static void main(String[] args) {
		
		Consumer<String> consumer = new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.print(t);
			}
		};
		
		List<String> list = getList();
		
		list.forEach(consumer);
		
		System.out.println();
		list.forEach(t->System.out.print(t));
		
		System.out.println();
		list.forEach(System.out::print);
	}
	
	private static List<String> getList() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			list.add(i+" ");
		}
		return list;
	}
}
