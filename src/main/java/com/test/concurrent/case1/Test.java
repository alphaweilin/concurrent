package com.test.concurrent.case1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Test {
	public static void main(String[] args) {
//		String[] str = new String[] { "yang", "hao" };     
//		List list = Arrays.asList(str); 
//		list.add("123");
		
//		System.out.println(8>>1);
		test();
	}
	
	public static void test() {
		int i = 6;
		i = i++;
		System.out.println(i);
		
		Map<String, String> map = new HashMap<String, String>();
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
	}
}