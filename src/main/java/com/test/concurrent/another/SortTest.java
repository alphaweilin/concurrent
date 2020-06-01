package com.test.concurrent.another;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SortTest {

	public static void main(String[] args) {
		System.out.println("sort list");
		sortList();
		System.out.println("sort list2");
		sortList2();
		System.out.println("sort arry");
		sortArray();
		System.out.println("sort arry2");
		sortArray2();
		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void sortList() {
		List<SortDTO> list = Arrays.asList(new SortDTO("300"), new SortDTO("50"), new SortDTO("200"),
				new SortDTO("220"));
//		Collections.sort(list, (o1, o2) -> o1.getSortTarget().compareTo(o2.getSortTarget()));
		list.sort((o1, o2) -> o1.getSortTarget().compareTo(o2.getSortTarget()));
		list.forEach(f -> System.out.println(f.getSortTarget()));
	}
	
	public static void sortList2() {
		List<SortDTO2> list = Arrays.asList(new SortDTO2("300"), new SortDTO2("50"), new SortDTO2("200"),
				new SortDTO2("220"));
		Collections.sort(list);
		list.forEach(f -> System.out.println(f.getSortTarget()));
	}

	public static void sortArray() {
		SortDTO[] array = new SortDTO[] { new SortDTO("300"), new SortDTO("50"), new SortDTO("200"),
				new SortDTO("220") };
//		Arrays.sort(array, Comparator.comparing(SortDTO::getSortTarget));
		Arrays.sort(array, Comparator.comparing(e -> e.getSortTarget()));
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i].getSortTarget());
		}

	}
	
	public static void sortArray2() {
		SortDTO2[] array = new SortDTO2[] { new SortDTO2("300"), new SortDTO2("50"), new SortDTO2("200"),
				new SortDTO2("220") };
		Arrays.sort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i].getSortTarget());
		}

	}

}

class SortDTO {
	private String sortTarget;

	public SortDTO(String sortTarget) {
		this.sortTarget = sortTarget;
	}

	public String getSortTarget() {
		return sortTarget;
	}

	public void setSortTarget(String sortTarget) {
		this.sortTarget = sortTarget;
	}
}

class SortDTO2 implements Comparable<SortDTO2> {
	private String sortTarget;

	public SortDTO2(String sortTarget) {
		this.sortTarget = sortTarget;
	}

	public String getSortTarget() {
		return sortTarget;
	}

	public void setSortTarget(String sortTarget) {
		this.sortTarget = sortTarget;
	}

	@Override
	public int compareTo(SortDTO2 o) {
		return this.sortTarget.compareTo(o.getSortTarget());
	}
}

