package com.zoo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueTest {

	public static void main(String[] args) {
		ArrayBlockingQueue<Apple> abq = new ArrayBlockingQueue<>(2);
		new Thread(new Producer(abq)).start();
		new Thread(new Producer(abq)).start();
		new Thread(new Consumer(abq)).start();
		new Thread(new Consumer(abq)).start();
	}
	//生产者线程
	static class Producer implements Runnable {
		private final BlockingQueue<Apple> queue;

		Producer(BlockingQueue<Apple> q) {
			queue = q;
		}

		public void run() {
			try {
				while (true) {
					Apple apple = new Apple();
					System.out.println("生产：" + apple);
					queue.put(apple);
				}
			} catch (InterruptedException ex) {
			}
		}

	}
	//消费者线程
	static class Consumer implements Runnable {
		private final BlockingQueue<Apple> queue;

		Consumer(BlockingQueue<Apple> q) {
			queue = q;
		}

		public void run() {
			try {
				while (true) {
					Apple apple = queue.take();
					System.out.println("消费：" + apple);
				}
			} catch (InterruptedException ex) {
			}
		}
	}
}

class Apple {}
