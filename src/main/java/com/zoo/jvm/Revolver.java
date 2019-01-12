package com.zoo.jvm;

public class Revolver {
	@SuppressWarnings("unused")
	private static int a = 1;
	@SuppressWarnings("unused")
	private static int b;
	@SuppressWarnings("unused")
	private static int c;
	
	static {
		b = 1;
	}
	static {
		b = 2;
	}
	
}
