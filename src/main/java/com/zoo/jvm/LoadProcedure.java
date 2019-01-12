package com.zoo.jvm;


/**
 * •加载：查找并加载类的二进制数据
 * •连接
 *		–验证：确保被加载的类的正确性
 * 		–准备：为类的静态变量分配内存，并将其初始化为默认值
 * 		–解析：把类中的符号引用转换为直接引用
 * •初始化('主动使用'时会初始化类)：为类的静态变量赋予正确的初始值
 *	
 * •主动使用（六种）
 * 		–创建类的实例
 * 		–访问某个类或接口的静态变量，或者对该静态变量赋值;访问类的运行时静态常量(静态常量值在编译时不能确定,在运行时可以确定)
 * 		–调用类的静态方法
 * 		–反射(如Class.forName("com.shengsiyuan.Test"))
 * 		–初始化一个类的子类
 * 		–Java虚拟机启动时被标明为启动类的类（Java Test）
 */
public class LoadProcedure {

	/**
	 * 以下静态变量等号左边在'连接:准备'阶段完成,等号右边在'初始化'阶段完成.
	 */
	private static LoadProcedure instance=new LoadProcedure();
	private static int count1;
	private static int count2=0;
	
	private LoadProcedure() {
		count1++;
		count2++;
	}
	
	public static LoadProcedure get() {
		return instance;
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		LoadProcedure instance=LoadProcedure.get();
		
		System.out.println(instance.count1);
		System.out.println(instance.count2);
	}

}
