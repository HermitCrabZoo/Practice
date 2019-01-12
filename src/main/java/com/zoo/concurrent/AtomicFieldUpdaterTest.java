package com.zoo.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 原子字段更新器测试
 *
 */
public class AtomicFieldUpdaterTest implements Runnable{
	//整型字段更新器
	AtomicIntegerFieldUpdater<Dimension> dimensionUpdater = AtomicIntegerFieldUpdater.
			newUpdater(Dimension.class, "amount");
	AtomicInteger ai = new AtomicInteger();
	Dimension dim;
	CountDownLatch cdl;
	
	public AtomicFieldUpdaterTest(Dimension dim,CountDownLatch cdl) {
		this.dim = dim;
		this.cdl = cdl;
	}
	
	@Override
	public void run() {
		for(int i = 0 ;i<10;i++) {
			if(Math.random()>0.5){
				dimensionUpdater.incrementAndGet(dim);
				ai.incrementAndGet();
			}
		}
		cdl.countDown();
	}
	
	public void print() {
		System.out.println("字段更新器结果："+dim.amount);
		System.out.println("标准结果："+ai.get());
	}
	
	public static void main(String[] args) throws InterruptedException {
		//引用字段更新器
		AtomicReferenceFieldUpdater<Nickname, String> nicknameUpdater = AtomicReferenceFieldUpdater.
				newUpdater(Nickname.class, String.class, "value");
		Nickname nn = new Nickname("nickname");
		nicknameUpdater.set(nn, "updater");
		
		int len = 1000;
		CountDownLatch cdl = new CountDownLatch(len);
		Dimension dim = new Dimension();
		AtomicFieldUpdaterTest aiat = new AtomicFieldUpdaterTest(dim,cdl);
		// 创建10条线程
		for (int k = 0; k < len; k++) {
			new Thread(aiat).start();
		}
		cdl.await();
		aiat.print();
		System.out.println(nn);
	}
}
class Dimension{
	volatile int amount;
}
class Nickname{
	volatile String value;
	public Nickname(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Nickname [value=" + value + "]";
	}
}
