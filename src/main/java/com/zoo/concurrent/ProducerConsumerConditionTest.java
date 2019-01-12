package com.zoo.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//生产者消费者模式，使用Lock来同步，Condition来等待和唤醒
public class ProducerConsumerConditionTest {
	public static void main(String[] args) throws InterruptedException {
		//店员
		Clerk clerk=new Clerk();
		CountDownLatch cdl = new CountDownLatch(4);
		Producer producer=new Producer(clerk,cdl);
		Consumer consumer=new Consumer(clerk,cdl);
		
		new Thread(producer,"生产者A").start();
		new Thread(producer,"生产者B").start();
		
		new Thread(consumer,"消费者A").start();
		new Thread(consumer,"消费者B").start();
		cdl.await();
		clerk.report();
	}
}

class Clerk{
	
	private int capacity=2;
	private int product=0;
	
	private Lock lock = new ReentrantLock();
	private Condition producer_condition=lock.newCondition();
	private Condition consumer_condition=lock.newCondition();
	
	private int p_total = 0;
	private int s_total = 0;
	
	public Clerk() {
		
	}

	public void get() {
		lock.lock();
		try {
			while (product>=capacity) {//避免虚假唤醒，释放锁的步骤总是在循环中
				System.out.println(Thread.currentThread().getName()+"：产品已满！");
				try {
					producer_condition.await();//生产者等待，并释放锁
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName()+"："+ ++product);//进货
			p_total++;
			consumer_condition.signalAll();//唤醒其他所有等待的消费者线程
		} finally {
			lock.unlock();
		}
	}
	
	public void sale() {
		lock.lock();
		try {
			while (product<=0) {//避免虚假唤醒，释放锁的步骤总是在循环中
				System.out.println(Thread.currentThread().getName()+"：缺货！");
				try {
					consumer_condition.await();//消费者等待，并释放锁
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName()+"："+ --product);//售货
			s_total++;
			producer_condition.signalAll();//唤醒其他所有等待的生产者线程
		} finally {
			lock.unlock();
		}
	}
	public void report() {
		System.out.println(String.format("总共生产：%d，总共消费：%d", p_total,s_total));
	}
}

class Producer implements Runnable{
	private Clerk clerk;
	private CountDownLatch cdl;

	public Producer(Clerk clerk,CountDownLatch cdl) {
		this.clerk = clerk;
		this.cdl = cdl;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			clerk.get();//生产一个
		}
		cdl.countDown();
	}
}


class Consumer implements Runnable{
	private Clerk clerk;
	private CountDownLatch cdl;
	
	public Consumer(Clerk clerk,CountDownLatch cdl) {
		this.clerk = clerk;
		this.cdl = cdl;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			clerk.sale();//消费一个
		}
		cdl.countDown();
	}
}

