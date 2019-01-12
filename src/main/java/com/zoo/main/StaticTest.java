package com.zoo.main;

public class StaticTest {
	
	public StaticTest() {
		System.out.println("StaticTest constructor with out params");
	}
	
	
	public StaticTest(String param) {
		System.out.println("StaticTest constructor with in params:"+param);
	}
	
	
	
	private static class StaticInner{
		private static final StaticTest singleton=new StaticTest("单例");
	}
	
	
	public static StaticTest getInstance() {
		return StaticInner.singleton;
	}
	
}
