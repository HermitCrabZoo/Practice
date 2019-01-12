package com.zoo.java8.functionalInterface;


//此注解表名该接口为函数式接口,只能有一个抽象方法
//未使用此注解的只有一个抽象方法的接口同样是函数式接口，在编译时虚拟机会自动为该接口加上此注解
@java.lang.FunctionalInterface
public interface FunctionalInterface {
	//此为默认方法,可有多个默认方法
	default void bigMouth(String dirtyMessage){
		System.out.println(dirtyMessage);
	}
	
	//第二个默认方法
	default Double canDouble(Object cd){
		return Double.parseDouble(String.valueOf(cd));
	}
	
	
	void doIt();//只有一个抽象方法的接口为函数式接口
}
