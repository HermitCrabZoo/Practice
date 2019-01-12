package com.zoo.concurrent;


/*
 * 线程八锁：八种常见注意事项
 * 
 * 1、两个普通同步方法，两个线程标准打印，one two
 * 2、新增Thread.sleep()给printOne()，打印,one two
 * 3、新增普通方法printThree()方法打印，three one two
 * 4、两个普通的同步方法，两个Number对象，打印，two one
 * 5、修改printOne()为静态方法，一个Number对象，打印，two one
 * 6、修改两个方法均为静态方法，一个Number对象，打印，one two
 * 7、一个静态方法，一个非静态方法，连个Number对象，打印，two one
 * 8、两个静态方法，两个Number对象，打印，one two
 * 
 * 关键点：
 * 一非静态方法的锁为this，静态方法的锁为对应的Class实例
 * 二在某一个时刻内，只能有一个线程持有锁，无论几个方法
 */
public class ThreadEightLockTest {

	public static void main(String[] args) {
		
//		Number number1=new Number();
		Number number2=new Number();
		
		new Thread(Number::printOne).start();
		new Thread(number2::printTwo).start();
//		new Thread(number1::printThree).start();
		
	}

}



class Number{
	public static synchronized void printOne() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("one");
	}
	
	public synchronized void printTwo() {
		System.out.println("two");
	}
	
	public void printThree() {
		System.out.println("three");
	}
}