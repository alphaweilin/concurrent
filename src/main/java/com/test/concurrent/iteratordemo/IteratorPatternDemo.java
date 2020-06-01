package com.test.concurrent.iteratordemo;

public class IteratorPatternDemo {
	public static void main(String[] args) {
		NameRepository namesRepository = new NameRepository();

		for (Iterator iter = namesRepository.getIterator(); iter.hasNext();) {
			String name = (String) iter.next();
			System.out.println("Name : " + name);
		}
		
//		String[] array = new String[] {"1","2","3"};
//		int index=0;
//		System.out.println(index++);
//		System.out.println(++index);
	}
}
