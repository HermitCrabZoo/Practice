package com.zoo.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * 原子更新引用，降低ABA问题出现的几率
 *
 */
public class AtomicMarkableReferenceTest {
	
	CountDownLatch cdl = new CountDownLatch(3);
	//使用127是因为127在IntegerCache里，这与threadC中设置的新值127，其实是同一个对象
	//因此这样可以制造ABA问题，以此来测试AtomicMarkableReference能否解决ABA问题
	AtomicMarkableReference<Integer> amr = new AtomicMarkableReference<Integer>(127,false);
	
	Thread threadA = new Thread(()-> {
		Integer old = amr.getReference();
		boolean mark = amr.isMarked();
		String name = Thread.currentThread().getName();
		System.out.printf("①线程%s读取到：value=%d, mark=%b%n",name,old,mark);
		try {
			Thread.sleep(Long.MAX_VALUE);//线程A读取后休眠，模拟耗时任务。
		} catch (InterruptedException e) {}
		System.out.printf("⑥线程%s比较：value=%d, mark=%b 设置：value=%d，是否成功：%b%n",
				name,old,mark,1000,amr.compareAndSet(old, 1000, mark, !mark));
		cdl.countDown();
	},"A");
	
	Thread threadC = new Thread(()-> {
		try {
			Thread.sleep(Long.MAX_VALUE);//一开始就休眠，等B线程读写完成再继续
		} catch (InterruptedException e) {}
		Integer old = amr.getReference();
		boolean mark = amr.isMarked();
		String name = Thread.currentThread().getName();
		System.out.printf("④线程%s读取到：value=%d, mark=%b%n",name,old,mark);
		System.out.printf("⑤线程%s比较：value=%d, mark=%b 设置：value=%d，是否成功：%b%n",
				name,old,mark,127,amr.compareAndSet(old, 127, mark, !mark));
		threadA.interrupt();//通知线程A去"比较和写入"
		cdl.countDown();
	},"C");
	
	Thread threadB = new Thread(()-> {
		Integer old = amr.getReference();
		boolean mark = amr.isMarked();
		String name = Thread.currentThread().getName();
		System.out.printf("②线程%s读取到：value=%d, mark=%b%n",name,old,mark);
		System.out.printf("③线程%s比较：value=%d, mark=%b 设置：value=%d，是否成功：%b%n",
				name,old,mark,100,amr.compareAndSet(old, 100, mark, !mark));
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
		System.out.println("最后的结果："+amr.getReference());
	}
	
	public static void main(String[] args) throws InterruptedException {
		new AtomicMarkableReferenceTest().run();
	}
}
