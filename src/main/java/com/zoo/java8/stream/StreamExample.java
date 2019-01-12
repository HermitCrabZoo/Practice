package com.zoo.java8.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamExample {
	public static void main(String[] args) {
		Random random = new Random();
		//输出10个随机数
		System.out.println("未排序:");
		random.ints().limit(10).forEach(System.out::println);
		System.out.println("排序:");
		random.ints().limit(10).sorted().forEach(System.out::println);
		
		
		
		
		List<Integer> numbers = Arrays.asList(2, 3, 3, 2, 5, 2, 7);
		//get list of unique squares,获取numbers元素平方后的不重复的值的list
		List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
		System.out.println("去重复:");
		squaresList.forEach(System.out::println);
		
		IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
		System.out.println("Highest number in List : " + stats.getMax());
		System.out.println("Lowest number in List : " + stats.getMin());
		System.out.println("Sum of all numbers : " + stats.getSum());
		System.out.println("Average of all numbers : " + stats.getAverage());
		
		
		
		
		List<String> strings = Arrays.asList("efg", "", "abc", "bc", "ghij","", "lmn");
		//get count of empty string,获取空字符串的个数
		long count = strings.stream().filter(string -> string.isEmpty()).count();
		System.out.println("线性流过滤空值个数:");
		System.out.println(String.valueOf(count));
		//并行流
		count = strings.parallelStream().filter(string -> string.isEmpty()).count();
		System.out.println("并行流过滤空值个数:");
		System.out.println(String.valueOf(count));
		//过滤非空的收集到list里面
		List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
		System.out.println("Filtered List: " + filtered);
		//过滤到的非空的用','连接成一个字符串
		String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
		System.out.println("Merged String: " + mergedString);
		
		
	}
	
	
}
