package com.zoo.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 一、实现Callable：第三种创建线程的方式
 * 二、执行Callable需要FutureTask类的支持，用于接收返回结果，FutureTask是Future的实现类
 * @author ZOO
 *
 */
public class CallableTest {

	public static void main(String[] args) {
		Callabler callable=new Callabler();
		
		FutureTask<Integer> ft=new FutureTask<>(callable);
		
		new Thread(ft).start();
		
		try {
			Integer sum=ft.get();//调用此方法会等待子线程执行完成，可以用于闭锁
			System.out.println("sum:"+sum);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}


class Callabler implements Callable<Integer>{
	@Override
	public Integer call() throws Exception {
		int sum=0;
		for (int i = 0; i <= 100; i++) {
			System.out.println(i);
			sum+=i;
		}
		return sum;
	}
}