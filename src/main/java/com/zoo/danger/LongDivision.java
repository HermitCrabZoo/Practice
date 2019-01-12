package com.zoo.danger;

public class LongDivision {
	public static void main(String[] args) {
		long Milli_Second = 24 * 60 * 60 * 1000;//以int来运算，运算过程不会溢出
		long Micro_Second = 24 * 60 * 60 * 1000 * 1000;//以int值来运算，运算过程已经溢出

		System.out.println(Micro_Second / Milli_Second);//结果:5

		Milli_Second = 24L * 60 * 60 * 1000;//以long类型来运算
		Micro_Second = 24L * 60 * 60 * 1000 * 1000;//以long类型来运算

		System.out.println(Micro_Second / Milli_Second);//结果:1000
		

	}
}
