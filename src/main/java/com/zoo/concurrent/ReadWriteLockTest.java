package com.zoo.concurrent;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * 写写/读写需要'互斥'
 * 
 * 读读不需要互斥
 * 
 */
public class ReadWriteLockTest {

	public static void main(String[] args) {
		ReadWriteLockDemo readWriteLockDemo=new ReadWriteLockDemo();
		
		new Thread(()->{
			readWriteLockDemo.set((int) (Math.random()*101));
		},"Writer").start();
		
		for (int i = 0; i < 100; i++) {
			new Thread(()->{
				readWriteLockDemo.get();
			},"Reader"+i).start();
		}
		
	}

}


class ReadWriteLockDemo {
	
	private int number ;
	
	private ReadWriteLock lock=new ReentrantReadWriteLock();
	
	//读
	public void get() {
		lock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"："+number);
			
		} finally {
			lock.readLock().unlock();
		}
	}
	
	public void set(int number) {
		
		lock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"："+number);
			this.number=number;
		} finally {
			lock.writeLock().unlock();
		}
	}
	
}