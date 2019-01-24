package com.zoo.concurrent;

import java.util.stream.IntStream;

public class ThreadTest extends Thread{
	
	public ThreadTest(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		IntStream.range(0, 5).forEach(i->System.out.println(ThreadTest.this.getName()+": "+i));
	}
	
	public static void main(String[] args) {
		ThreadTest threadA = new ThreadTest("A");
		ThreadTest threadB = new ThreadTest("B");
		threadA.setPriority(10);
		threadB.setPriority(1);
		threadA.start();
		threadB.start();
		
		if(Thread.activeCount() >= 1) {
			Thread.yield();
		}
	}

}
