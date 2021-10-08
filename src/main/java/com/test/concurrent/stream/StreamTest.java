package com.test.concurrent.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * refer https://www.jianshu.com/p/ceb7bf515c03,
 * https://developer.ibm.com/zh/languages/java/articles/j-lo-java8streamapi/
 * 
 * @author WeiLin
 *
 */
public class StreamTest {

	public static void main(String[] args) {
		test13();
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
		Optional<Double> totalPrice2 = books.stream().map(Book::getPrice).reduce(Double::sum);
		System.out.println(totalPrice.get());
		System.out.println(totalPrice2.get());
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

		// .stream() return type Stream<List<String>>
		// .flatMap() return type Stream<String>
		Stream<String> playerStream = playersInWorldCup2016.stream().flatMap(pList -> pList.stream());
		playerStream.forEach(System.out::println);

//		playersInWorldCup2016.forEach(System.out::println);

	}

	public static void test6() {
		List<Book> books = Arrays.asList(new Book("Java编程思想", "Bruce Eckel", "机械工业出版社", 108.00D),
				new Book("Java 8实战", "Mario Fusco", "人民邮电出版社", 79.00D),
				new Book("MongoDB权威指南（第2版）", "Kristina Chodorow", "人民邮电出版社", 69.00D));
		DoubleSummaryStatistics doubleSummaryStatistics = books.stream().mapToDouble((x) -> x.getPrice())
				.summaryStatistics();
		System.out.println(doubleSummaryStatistics.getSum());
	}

	public static void test7() {
		List<Book> transactions = Arrays.asList(new Book("Java编程思想", "Bruce Eckel", "机械工业出版社", 108.00D),
				new Book("Java 8实战", "Mario Fusco", "人民邮电出版社", 79.00D),
				new Book("MongoDB权威指南（第2版）", "Kristina Chodorow", "人民邮电出版社", 69.00D));
		List<String> bookNameList = transactions.parallelStream().filter(t -> t.getPublication() == "人民邮电出版社")
				.sorted(Comparator.comparing(Book::getPrice)).map(Book::getName).collect(Collectors.toList());

		bookNameList.forEach(System.out::println);

	}

	public static void test8() {
		Optional<String> concat = Stream.of("A", "B", "C", "D").reduce((a, b) -> a.concat(b));
		String concat1 = Stream.of("A", "B", "C", "D").reduce("", String::concat);
		System.out.println(concat.get());
	}

	public static void test9() {
		Random random = new Random();
		Supplier<Integer> randomSupplier = random::nextInt;

		Stream.generate(randomSupplier).limit(10).forEach(System.out::println);
	}

	public static void test10() {
		Stream.iterate(0, n -> n + 3).limit(10).forEach(System.out::println);
	}

	public static void test11() {
		List<Book> books = Arrays.asList(new Book("Java编程思想", "Bruce Eckel", "机械工业出版社", 108.00D),
				new Book("Java 8实战", "Mario Fusco", "人民邮电出版社", 79.00D),
				new Book("MongoDB权威指南（第2版）", "Kristina Chodorow", "人民邮电出版社", 69.00D));
		Map<String, List<Book>> resultMap = books.stream().collect(Collectors.groupingBy(Book::getPublication));
		for (Entry<String, List<Book>> entry : resultMap.entrySet()) {
			System.out.println(entry.getKey());
		}

		Map<Boolean, List<Book>> resultMap2 = books.stream()
				.collect(Collectors.partitioningBy(book -> book.getPrice() > 20));
		for (Entry<Boolean, List<Book>> entry : resultMap2.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().size());
		}
	}

	public static void test12() {
		List<Book> books = Arrays.asList(new Book("Java编程思想", "Bruce Eckel", "机械工业出版社", 108.00D),
				new Book("Java 8实战", "Mario Fusco", "人民邮电出版社", 79.00D),
				new Book("MongoDB权威指南（第2版）", "Kristina Chodorow", "人民邮电出版社", 69.00D),
				new Book("MongoDB", "Kristina Chodorow", "人民邮电出版社", 50.00D),
				new Book("MongoDB", "Kristina Chodorow", "人民邮电出版社", 50.00D));

		Map<String, List<Book>> booksGroup = books.stream().collect(Collectors.groupingBy(book -> {
			if (book.getPrice() > 0 && book.getPrice() <= 50) {
				return "A";
			} else if (book.getPrice() > 50 && book.getPrice() <= 100) {
				return "B";
			} else {
				return "C";
			}
		}));
		
		for (Entry entry:booksGroup.entrySet()) {
			System.out.println(entry.getKey()+":"+entry.getValue().toString());
		}
		
		Map<String, Map<String, List<Book>>> booksGroup2 = books.stream().collect(
		        Collectors.groupingBy(Book::getPublication, Collectors.groupingBy(book -> {
		            if (book.getPrice() > 0 && book.getPrice() <= 50) {
		                return "A";
		            } else if (book.getPrice() > 50 && book.getPrice() <=100) {
		                return "B";
		            } else {
		                return "C";
		            }
		        }))
		);
		
		for (Entry entry:booksGroup2.entrySet()) {
			System.out.println(entry.getKey()+":"+entry.getValue().toString());
		}
		
	}
	
	public static void test13() {
		List<Book> books = Arrays.asList(new Book("Java编程思想", "Bruce Eckel", "机械工业出版社", 108.00D),
				new Book("Java 8实战", "Mario Fusco", "人民邮电出版社", 79.00D),
				new Book("MongoDB权威指南（第2版）", "Kristina Chodorow", "人民邮电出版社", 69.00D),
				new Book("MongoDB", "Kristina Chodorow", "人民邮电出版社", 50.00D),
				new Book("MongoDB", "Kristina Chodorow", "人民邮电出版社", 50.00D));
		List<String> list = books.stream().map(Book::getName).distinct().collect(Collectors.toList());
		System.out.println(list);
	}
	
	 

}
