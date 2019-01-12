package com.zoo.danger;

public class ThreeExpression {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		char x='X';
		int i=0;
		
		//0为int常量，整个表达式的类型为x的类型，
		System.out.println(true?x:0);
		
		//i为int类型的变量，整个表达式的类型为x提升后的类型,x转换成提升后的int类型的值为88
		System.out.println(false?i:x);
	}
}
