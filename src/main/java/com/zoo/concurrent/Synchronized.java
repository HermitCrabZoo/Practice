package com.zoo.concurrent;

import java.util.concurrent.CountDownLatch;

public class Synchronized {

	public static void main(String[] args) throws InterruptedException {
		int l = 2;
		CountDownLatch cdl = new CountDownLatch(l);
		Sync sync =new Sync(cdl);
		for(int i = 0;i<l;i++) {
			Thread thread = new Thread(sync);//使用同一个锁
			thread.start();
		}
		
		cdl.await();
		
		System.out.println(Sync.i);
	}

}
class Sync implements Runnable{
	CountDownLatch cdl = null;
	static int i = 0;
	
	public Sync(CountDownLatch cdl) {
		this.cdl = cdl;
	}
	
	public void increase() {
		synchronized (this) {
			i++;
		}
	}
	
	@Override
	public void run() {
		for(int j=0;j<1000000;j++){
            increase();
        }
		cdl.countDown();
	}
	
}