package com.zoo.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//ABC交替打印n次
public class AlternateTest {

	public static void main(String[] args) {
		Alternate alternate=new Alternate();
		
		new Thread(()->{
			for (int i = 1; i <= 10; i++) {
				alternate.printA(i);
			}
		},"A").start();
		
		new Thread(()->{
			for (int i = 1; i <= 10; i++) {
				alternate.printB(i);
			}
		},"B").start();
		
		new Thread(()->{
			for (int i = 1; i <= 10; i++) {
				alternate.printC(i);
				System.out.println("-------------------------------");
			}
		},"C").start();
		
	}

}

class Alternate{
	
	private int total=1;
	
	private int flag=1;//打印哪一个的标记
	
	private Lock lock=new ReentrantLock();
	
	private Condition a=lock.newCondition();
	private Condition b=lock.newCondition();
	private Condition c=lock.newCondition();
	
	public void printA(int totalLoop) {
		lock.lock();
		try {
			
			//等待并释放锁
			if (flag!=1) {
				a.await();
			}
			
			//打印
			for (int i = 1; i <= total; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop);
			}
			
			//让下一个步骤去执行
			flag=2;
			b.signal();//只唤醒b
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	
	public void printB(int totalLoop) {
		lock.lock();
		try {
			
			//等待并释放锁
			if (flag!=2) {
				b.await();
			}
			
			//打印
			for (int i = 1; i <= total; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop);
			}
			
			//让下一个步骤去执行
			flag=3;
			c.signal();//只唤醒c
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	
	public void printC(int totalLoop) {
		lock.lock();
		try {
			
			//等待并释放锁
			if (flag!=3) {
				c.await();
			}
			
			//打印
			for (int i = 1; i <= total; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop);
			}
			
			//让下一个步骤去执行
			flag=1;
			a.signal();//只唤醒a
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	
}