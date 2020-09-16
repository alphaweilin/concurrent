package com.test.concurrent.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * refer https://www.jianshu.com/p/ceb7bf515c03
 * 
 * @author WeiLin
 *
 */
public class StreamTest {

	public static void main(String[] args) {
		test5();
	}

	public static void test1() {
		// 外部
		List<String> list = Arrays.asList("A", "B", "C", "D");
		for (String str : list) {
			System.out.println(str);
		}

		// 内部
		list.stream().forEach(System.out::println);
	}

	public static void test2() {
		Stream.of(1, 8, 5, 2, 1, 0, 9, 2, 0, 4, 8).filter(n -> n > 2) // 对元素过滤，保留大于2的元素
				.distinct() // 去重，类似于SQL语句中的DISTINCT
				.skip(1) // 跳过前面1个元素
				.limit(2) // 返回开头2个元素，类似于SQL语句中的SELECT TOP
				.sorted() // 对结果排序
				.forEach(System.out::println);

		// filter后剩下：8，5，9，4，8
		// 去重后排序剩下：5，9
	}

	public static void test3() {
		boolean hasMatch = Stream.of("Java", "C#", "PHP", "C++", "Python").anyMatch(s -> s.equals("Java"));
		// hasMatch:true
		boolean hasAllMatch = Stream.of("Java", "C#", "PHP", "C++", "Python").allMatch(s -> s.contains("#"));
		// hasAllMatch:false
		Optional<String> element = Stream.of("Java", "C#", "PHP", "C++", "Python").filter(s -> s.contains("C"))
				// .findFirst() // 查找第一个元素
				.findAny(); // 查找任意元素
		// element：Optional[C#]
		System.out.println(element);
	}

	public static void test4() {
		List<Book> books = Arrays.asList(new Book("Java编程思想", "Bruce Eckel", "机械工业出版社", 108.00D),
				new Book("Java 8实战", "Mario Fusco", "人民邮电出版社", 79.00D),
				new Book("MongoDB权威指南（第2版）", "Kristina Chodorow", "人民邮电出版社", 69.00D));
		// 计算所有图书的总价
		Optional<Double> totalPrice = books.stream().map(Book::getPrice).reduce((n, m) -> n + m);
		// 价格最高的图书
		Optional<Book> expensive = books.stream().max(Comparator.comparing(Book::getPrice));
		// 价格最低的图书
		Optional<Book> cheapest = books.stream().min(Comparator.comparing(Book::getPrice));
		// 计算总数
		long count = books.stream().count();
	}

	public static void test5() {
		List<String> teamIndia = Arrays.asList("Virat", "Dhoni", "Jadeja");
		List<String> teamAustralia = Arrays.asList("Warner", "Watson", "Smith");
		List<String> teamEngland = Arrays.asList("Alex", "Bell", "Broad");
		List<String> teamNewZeland = Arrays.asList("Kane", "Nathan", "Vettori");
		List<String> teamSouthAfrica = Arrays.asList("AB", "Amla", "Faf");
		List<String> teamWestIndies = Arrays.asList("Sammy", "Gayle", "Narine");
		List<String> teamSriLanka = Arrays.asList("Mahela", "Sanga", "Dilshan");
		List<String> teamPakistan = Arrays.asList("Misbah", "Afridi", "Shehzad");

		List<List<String>> playersInWorldCup2016 = new ArrayList<>();
		playersInWorldCup2016.add(teamIndia);
		playersInWorldCup2016.add(teamAustralia);
		playersInWorldCup2016.add(teamEngland);
		playersInWorldCup2016.add(teamNewZeland);
		playersInWorldCup2016.add(teamSouthAfrica);
		playersInWorldCup2016.add(teamWestIndies);
		playersInWorldCup2016.add(teamSriLanka);
		playersInWorldCup2016.add(teamPakistan);

//		List<String> flatMapList = playersInWorldCup2016.stream().flatMap(pList -> pList.stream())
//				.collect(Collectors.toList());
//		System.out.println(flatMapList);
		
		//.stream() return type Stream<List<String>>
		//.flatMap() return type Stream<String>
		Stream<String> playerStream = playersInWorldCup2016.stream().flatMap(pList -> pList.stream());
		playerStream.forEach(System.out::println);
		
//		playersInWorldCup2016.forEach(System.out::println);

	}

	public static void test6() {

	}

	public static void test7() {

	}

}
