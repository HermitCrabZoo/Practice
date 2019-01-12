package com.zoo.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 原子更新引用，解决ABA问题
 *
 */
public class AtomicStampedReferenceTest {
	
	CountDownLatch cdl = new CountDownLatch(3);
	AtomicStampedReference<Integer> asr = new AtomicStampedReference<Integer>(200,0);
	
	Thread threadA = new Thread(()-> {
		Integer old = asr.getReference();
		int stamp = asr.getStamp();
		String name = Thread.currentThread().getName();
		System.out.printf("①线程%s读取到：value=%d, stamp=%d%n",name,old,stamp);
		try {
			Thread.sleep(Long.MAX_VALUE);//线程A读取后休眠，模拟耗时任务。
		} catch (InterruptedException e) {}
		System.out.printf("⑥线程%s比较：value=%d, stamp=%d 设置：value=%d, stamp=%d，是否成功：%b%n",
				name,old,stamp,1000, stamp+1,asr.compareAndSet(old, 1000, stamp, stamp+1));
		cdl.countDown();
	},"A");
	
	Thread threadC = new Thread(()-> {
		try {
			Thread.sleep(Long.MAX_VALUE);//一开始就休眠，等B线程读写完成再继续
		} catch (InterruptedException e) {}
		Integer old = asr.getReference();
		int stamp = asr.getStamp();
		String name = Thread.currentThread().getName();
		System.out.printf("④线程%s读取到：value=%d, stamp=%d%n",name,old,stamp);
		System.out.printf("⑤线程%s比较：value=%d, stamp=%d 设置：value=%d, stamp=%d，是否成功：%b%n",
				name,old,stamp,200, stamp+1,asr.compareAndSet(old, 200, stamp, stamp+1));
		threadA.interrupt();//通知线程A去"比较和写入"
		cdl.countDown();
	},"C");
	
	Thread threadB = new Thread(()-> {
		Integer old = asr.getReference();
		int stamp = asr.getStamp();
		String name = Thread.currentThread().getName();
		System.out.printf("②线程%s读取到：value=%d, stamp=%d%n",name,old,stamp);
		System.out.printf("③线程%s比较：value=%d, stamp=%d 设置：value=%d, stamp=%d，是否成功：%b%n",
				name,old,stamp,100, stamp+1,asr.compareAndSet(old, 100, stamp, stamp+1));
		threadC.interrupt();//通知C线程去"读取、比较和写入"
		cdl.countDown();
	},"B");
	
	public void run() throws InterruptedException {
		threadA.start();
		threadC.start();
		//这里的休眠为了线程A在线程B修改前就读取到值，线程C在线程B中断线程C的休眠前进行休眠。
		TimeUnit.SECONDS.sleep(1);
		threadB.start();
		cdl.await();
		System.out.println("最后的结果："+asr.getReference());
	}
	
	public static void main(String[] args) throws InterruptedException {
		new AtomicStampedReferenceTest().run();
	}

}
