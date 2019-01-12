package com.zoo.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子更新数组测试
 *
 */
public class AtomicIntegerArrayTest implements Runnable {
	static AtomicIntegerArray aia = new AtomicIntegerArray(10);
	CountDownLatch cdl;
	
	public AtomicIntegerArrayTest(CountDownLatch cdl) {
		this.cdl = cdl;
	}

	@Override
	public void run() {
		for (int k = 0; k < 10000; k++) {
			// 执行数组中元素自增操作,参数为数组元素下标。
			aia.getAndIncrement(k % aia.length());
		}
		cdl.countDown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		int len = 10;
		CountDownLatch cdl = new CountDownLatch(len);
		AtomicIntegerArrayTest aiat = new AtomicIntegerArrayTest(cdl);
		// 创建10条线程
		for (int k = 0; k < len; k++) {
			new Thread(aiat).start();
		}
		cdl.await();
		//符合预期的输出[10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000]
		System.out.println(aia);
	}

}
