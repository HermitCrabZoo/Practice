package com.zoo.java8.lambda;

import java.util.ArrayList;
import java.util.List;

public class FunctionReference {
	public static void main(String[] args) {
		List<String> names = new ArrayList<String>();
        names.add("Peter");
        names.add("Linda");
        names.add("Smith");
        names.add("Zack");
        names.add("Bob");

        //     通过System.out::println引用了输出的方法
        names.forEach(System.out::println);
	}
}
