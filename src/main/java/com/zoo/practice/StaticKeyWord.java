package com.zoo.practice;

public class StaticKeyWord {
	
	public static void main(String[] args) {
		new Static1("");
		new Static2("");
		System.out.println("在外部调用构造方法前，类的静态属性始终在构造方法调用之前被定义/实例化,类的静态代码块始终在构造方法之前被调用。");
	}
	

}
class Static1{
	//调用此方法之前先调用"代码块",再调用此方法
	public Static1(String message){}
	
	//调用此方法之前先调用"代码块",再调用此方法
	public Static1() {
		System.out.println("Static1 constructor");//secondary, because 'new Static1()'
	}
	
	//此静态变量在静态代码块前被实例化一次
	@SuppressWarnings("unused")
	private static Static1 static1=new Static1();
	
	//此静态代码块在实例化静态变量后调用一次
	static{
		System.out.println("Static1 static block");//last, because after static field 
	}
	
	//此代码块在构造方法调用前始终被调用
	{
		System.out.println("Static1 block");//first,because before constructor
	}
	
}
class Static2{
	//调用此方法之前先调用"代码块",再调用此方法
	public Static2(String message){}
	
	//调用此方法之前先调用"代码块",再调用此方法
	public Static2() {
		System.out.println("Static2 constructor");//last,because 'new Static2()'
	}
	
	//此静态代码块若放在静态变量前则先调用一次
	static{
		System.out.println("Static2 static block");//first,because before static field
	}
	
	//静态变量在调用静态代码块后实例化一次
	@SuppressWarnings("unused")
	private static Static2 static2=new Static2();
	
	//此代码块在调用构造方法之前被调用，次数不限
	{
		System.out.println("Static2 block");//secondary,because before constructor
	}
}
