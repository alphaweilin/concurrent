package com.test.concurrent.case1;

import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
//		int i=6;
//		if((i=13) == 1) {
//			System.out.println(true);
//		}
		
//		if(i>1) {
//			System.out.println("1");
//		}else if(i>2) {
//			System.out.println("2");
//		}else if(i>3) {
//			System.out.println("3");
//		}
		
		
		String[] array = new String[]{"300", "50", "200", "220"};
		Arrays.sort(array);
//		list.forEach((f)->System.out.println(f));
		for(int i=0; i<array.length; i++) {
			System.out.println(array[i]);
		}
	}
}