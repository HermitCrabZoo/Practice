package com.zoo.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 解决同步问题的方式:
 * synchronized:隐式锁
 * 一、同步代码块
 * 二、同步方法
 * jdk1.5之后
 * 三、同步锁Lock：显式锁，需要通过lock()方法来上锁，unlock()方法来释放锁，更灵活，风险高
 */
public class LockTest {


	public static void main(String[] args) {
		Runnable runner=new Ticket();
		new Thread(runner,"一号窗口").start();
		new Thread(runner,"二号窗口").start();
		new Thread(runner,"三号窗口").start();
	}

}


class Ticket implements Runnable{
	private int tick=50;
	
	//可重入锁
	private Lock lock=new ReentrantLock();
	
	@Override
	public void run() {
		while(true) {
			try {
				lock.lock();
				if (tick>0) {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"完成售票，余票为："+--tick);
				}
			} finally {
				lock.unlock();
			}
		}
	}
	
}