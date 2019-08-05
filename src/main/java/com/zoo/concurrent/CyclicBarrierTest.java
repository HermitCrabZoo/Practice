package com.zoo.concurrent;

import java.util.concurrent.CyclicBarrier;

/**
 * 循环屏障测试
 * @author Hermi
 *
 */
public class CyclicBarrierTest {

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
			@Override
			public void run() {
				System.out.println("人到齐了，开会吧....");
			}
		});

		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + "到了");
				// 等待
				try {
					cyclicBarrier.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
