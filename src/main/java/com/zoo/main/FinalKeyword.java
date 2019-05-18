package com.zoo.main;

public class FinalKeyword {

	public static void main(String[] args) {
		onePlus();
	}

	private static void onePlus() {
		//常量池中的"onePlus2"
		String a = "onePlus2";
		//编译时常量
		final String b = "onePlus";
		//常量池中的"onePlus"
		String d = "onePlus";
		//由于b是编译时常量，且2也是编译时就能确定的值，所以c也是一个编译时常量，即c=b+2相当于c="onePlus2"。
		//而常量池中只可能存在一个"onePlus2"，所以a和c都是指向常量池中的同一个"onePlus2"。
		String c = b + 2;
		//由于d并不是使用final修饰，在使用d之前不知道d是什么值，所以e并不是一个编译时常量，e的值只能在运行期间确定，
		//所以e的值是在堆内存中保存。
		String e = d + 2;
		//输出true，false
		System.out.println((a == c));
		System.out.println((a == e));
	}
}
