package com.zoo.jvm;

/**
 * 类加载：双亲委托机制，先通过父加载器加载，若加载成功则返回Class实例，否则自己加载。
 * 根类加载器：Bootstrap
 * ---------扩展类加载器：Extension^
 * --------------------系统类加载器：System^
 * -------------------------------自定义加载器^
 *
 */
public class TestJVM {

	public static void main(String[] args) {
		MyClassLoader loader1=new MyClassLoader("loader1");//父加载器为：System
		loader1.setPath("E:\\Eclipse\\Practice\\bin\\main\\com\\zoo\\jvm");
		
		MyClassLoader loader2=new MyClassLoader(loader1,"loader2");//父加载器为:loader1
		loader2.setPath("E:\\Eclipse\\Practice\\bin\\main\\com\\zoo\\jvm");
		
		MyClassLoader loader3=new MyClassLoader(null,"loader3");//父加载器为：Bootstrap
		loader3.setPath("E:\\Eclipse\\Practice\\bin\\main");
		
		//委托父加载器加载成功
		load(loader2, "com.zoo.jvm.Dog");
		load(loader2, "com.zoo.jvm.Cat");
		
		//父加载器无法加载,自己加载成功
		load(loader3, "com.zoo.jvm.Dog");
		load(loader3, "com.zoo.jvm.Cat");
		
//		System.out.println(System.getProperty("sun.boot.class.path"));//Bootstrap加载的目录或jar
//		System.out.println(System.getProperty("java.ext.dirs"));//Extension加载的目录或jar
//		System.out.println(System.getProperty("java.class.path"));//System加载的目录或jar
		
	}
	
	@SuppressWarnings("unused")
	public static void load(ClassLoader loader,String name) {
		try {
			Class<?> clazz=loader.loadClass(name);
			Object obj=clazz.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
}
