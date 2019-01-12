package com.zoo.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * 一、线程池：提供一个线程队列，队列中保存着所有等待状态的线程。避免创建与销毁额外开销，提高了响应速度。
 * 
 * 二、线程池的体系结构：
 * 	java.util.concurrent.Executor：负责线程的使用与调度的根接口
 * 		|--ExecutorService 子接口：线程池的主要接口
 * 			|--ThreadPoolExecutor 线程池的实现类
 * 			|--ScheduledExecutorService 子接口：负责线程调度
 * 				|--ScheduledThreadPoolExecutor：继承ThreadPoolExecutor，实现ScheduledExecutorService接口，既有线程池功能，也有延迟调度功能
 * 
 * 三、工具类：Executors
 * ExecutorService newFixedThreadPool()：创建固定大小的线程池
 * ExecutorService newCachedThreadPool()：缓存线程池，线程池的数量不固定，可以根据需求自定更改数量。
 * ExecutorService newSingleThreadExecutor()：创建单个线程的线程池。
 * 
 * ScheduledExecutorService newScheduledThreadPool()：创建固定大小的线程池，可以延迟或定时的执行任务。
 */
public class ThreadPoolTest {
	
	public static void main(String[] args) {
		
		//创建线程池
		ExecutorService fixedPool=Executors.newFixedThreadPool(5);
		
		List<Future<?>> futures=new ArrayList<>();
		
//		ThreadPoolDemo task=new ThreadPoolDemo();
		
		for (int i = 0; i < 10; i++) {
//			futures.add(fixedPool.submit(task));
			
			Future<Integer> future=fixedPool.submit(()->{
				int sum=0;
				for (int j = 0; j <= 100; j++) {
					sum+=j;
				}
				return sum;
			});
			
			futures.add(future);
		}
		
		//关闭线程池
		fixedPool.shutdown();//平和的方式
		//fixedPool.shutdownNow();//暴力的方式
		futures.forEach(f->{
			try {
				System.out.println(f.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
		
		
		//---------------------------------------------------------------------
		
		//线程池延时调度
		ScheduledExecutorService pool=Executors.newScheduledThreadPool(5);
		Random random=new Random();
		for (int i = 0; i <= 10; i++) {
			Future<Integer> future=pool.schedule(()->{
				int num=random.nextInt(100);
				System.out.println(Thread.currentThread().getName()+"："+num);
				return num;
			}, 1, TimeUnit.SECONDS);
			try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		pool.shutdown();
		
	}
	
	
}

class ThreadPoolDemo implements Runnable{

	private int num=0;
	
	@Override
	public void run() {
		while (num<=100) {
			System.out.println(Thread.currentThread().getName()+"："+ num++);
		}
	}
	
	public void print() {
		System.out.println(num);
	}
	
}


