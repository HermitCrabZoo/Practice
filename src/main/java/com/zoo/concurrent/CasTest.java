package com.zoo.concurrent;

public class CasTest {


	public static void main(String[] args) {
		final CompareAndSwap cas=new CompareAndSwap();
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					boolean successed=false;
					while (!successed) {
						int expectedValue=cas.get();
						successed=cas.compareAndSet(expectedValue, expectedValue+1);
					}
					System.out.println(successed+":"+cas.get());
				}
			}).start();
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("final:"+cas.get());
	}

}

/**
 * 模拟CAS算法
 * @author ZOO
 *
 */
class CompareAndSwap{
	private int value;
	
	//读取值
	public synchronized int get() {
		return value;
	}
	
	//比较
	public synchronized int compareAndSwap(int expectedValue,int newValue) {
		
		int oldValue=value;
		
		//与期望值相同则修改
		if (value==expectedValue) {
			this.value=newValue;
		}
		
		return oldValue;
		
	}
	
	//设置值
	public synchronized boolean compareAndSet(int expectedValue,int newValue) {
		return expectedValue==compareAndSwap(expectedValue, newValue);
	}
}