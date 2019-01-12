package com.zoo.concurrent;

/**
 * 可重入锁测试
 *
 */
public class ReentryLockTest {
	public static void main(String[] args) throws InterruptedException {
		Child child = new Child();
		Thread t1 = new Thread(()->{
			child.increaseJ();
		});
		Thread t2 = new Thread(()->{
			child.increaseJ();
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		child.print();
	}
}

class Parent{
	static int i = 0;
	static int j = 0;
	
	protected synchronized void increaseI() {
		i++;
	}
	void print() {
		System.out.println(i);
		System.out.println(j);
	}
}

class Child extends Parent{
	public synchronized void increaseJ() {
		j++;
		increaseI();
	}
}