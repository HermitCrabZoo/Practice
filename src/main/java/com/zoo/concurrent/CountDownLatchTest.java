package com.zoo.concurrent;

import java.time.Clock;
import java.util.concurrent.CountDownLatch;

//CountDownLatch：闭锁，当完成某些运算时，需要等到其他操作完成后，当前运算才能执行
public class CountDownLatchTest {
	
	static Clock clock=Clock.systemUTC();

	public static void main(String[] args) {
		final CountDownLatch latch= new CountDownLatch(5);
		Runnable runner=new Latcher(latch);
		long start=clock.millis();
		for (int i = 0; i < 5; i++) {
			new Thread(runner).start();
		}
		
		try {
			latch.await();//等待执行完成
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end=clock.millis();
		System.out.println("Used times:"+(end-start));
	}
}

class Latcher implements Runnable{
	
	private CountDownLatch latch;

	
	public Latcher(CountDownLatch latch) {
		this.latch = latch;
	}


	@Override
	public void run() {
		synchronized (this) {
			for (int i = 0; i < 100; i++) {
				if (i % 2==0) {
					System.out.println(i);
				}
			}
			latch.countDown();//计数减一
		}
	}
	
}