package com.zoo.jvm;

public class Dog {
	static {
		System.out.println("Dog loaded by:"+Dog.class.getClassLoader());
	}
}
