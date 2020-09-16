package com.test.concurrent.case1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
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

//		Comparator<String> comparator = new Comparator<String>() {
//
//			@Override
//			public int compare(String o1, String o2) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//		};
//		comparator.equals("1");
	}

	public static void test() {
		int i = 6;
		i = i++;
		System.out.println(i);

		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "tom");
		map.put("2", "cat");
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			System.out.println(entry.getKey() + "," + entry.getValue());
		}
	}
}