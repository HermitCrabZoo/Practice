package com.zoo.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * ABA问题测试
 *
 */
public class ABATest {
	
	CountDownLatch cdl = new CountDownLatch(3);
	AtomicInteger ai = new AtomicInteger(10);
	
	Thread threadA = new Thread(()-> {
		int old = ai.get();
		String name = Thread.currentThread().getName();
		System.out.println("①线程"+name+"读取到："+old);
		try {
			Thread.sleep(Long.MAX_VALUE);//线程A读取后休眠，模拟耗时任务。
		} catch (InterruptedException e) {}
		System.out.println("⑥线程"+name+"比较"+old+"和设置值100："+ai.compareAndSet(old, 100));
		cdl.countDown();
	},"A");
	
	Thread threadC = new Thread(()-> {
		try {
			Thread.sleep(Long.MAX_VALUE);//一开始就休眠，等B线程读写完成再继续
		} catch (InterruptedException e) {}
		int old = ai.get();
		String name = Thread.currentThread().getName();
		System.out.println("④线程"+name+"读取到："+old);
		System.out.println("⑤线程"+name+"比较"+old+"和设置值10："+ai.compareAndSet(old, 10));
		threadA.interrupt();//通知线程A去"比较和写入"
		cdl.countDown();
	},"C");
	
	Thread threadB = new Thread(()-> {
		int old = ai.get();
		String name = Thread.currentThread().getName();
		System.out.println("②线程"+name+"读取到："+old);
		System.out.println("③线程"+name+"比较"+old+"和设置值20："+ai.compareAndSet(old, 20));
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
		System.out.println("最后的结果："+ai.get());
	}
	
	public static void main(String[] args) throws InterruptedException {
		new ABATest().run();
	}

}
