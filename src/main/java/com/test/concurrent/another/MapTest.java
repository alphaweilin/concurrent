package com.test.concurrent.another;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("key1", "lisi1");
		map.put("key2", "lisi2");
		map.put("key3", "lisi3");
		map.put("key4", "lisi4");
		String testString = "test";

		map.forEach((key, value) -> 
		System.out.println(key + " " + value));

		for (Map.Entry item : (Set<Map.Entry>) map.entrySet()) {
			System.out.println(item.getKey() + " " + item.getValue());
		}
		
		Iterator<Map.Entry<String, String>> intertor = map.entrySet().iterator();

	}

}
