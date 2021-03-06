package com.zoo.concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 *
 */
public class SpinLock {
	private AtomicReference<Thread> sign = new AtomicReference<>();

	public void lock() {
		Thread current = Thread.currentThread();
		while (!sign.compareAndSet(null, current)) {
		}
	}

	public void unlock() {
		Thread current = Thread.currentThread();
		sign.compareAndSet(current, null);
	}
}
