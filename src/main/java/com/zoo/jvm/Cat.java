package com.zoo.jvm;

public class Cat {
	static {
		System.out.println("Cat loaded by:"+Cat.class.getClassLoader());
	}
}
