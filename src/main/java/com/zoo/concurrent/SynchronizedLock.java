package com.zoo.concurrent;

import java.util.concurrent.TimeUnit;

public class SynchronizedLock implements Runnable {

	public static void main(String[] args) throws InterruptedException {
		
		SynchronizedLock sync = new SynchronizedLock();
		//第一个线程已经获取到锁，并且从不释放
		new Thread(sync).start();
		/*
		 * 第二个线程在等待获取锁，并且永远得不到锁。
		 * 尝试调用第二个线程的interrupt方法来中断第二个线程，结果是徒劳的。
		 */
        Thread t = new Thread(sync);
        TimeUnit.SECONDS.sleep(1);
        t.start();
        TimeUnit.SECONDS.sleep(1);
        //中断线程,无法生效
        t.interrupt();
	}

	public synchronized void locked() {
		System.out.println(Thread.currentThread().getName()+" inserted lock body！");
		while (true) // Never releases lock
			Thread.yield();
	}
	
	public void run() {
		// 中断判断
		while (!Thread.interrupted()) {
			System.out.println(Thread.currentThread().getName()+" 等待锁");
			locked();
		}
		System.out.println(Thread.currentThread().getName()+" 中断线程!!");
	}
}
