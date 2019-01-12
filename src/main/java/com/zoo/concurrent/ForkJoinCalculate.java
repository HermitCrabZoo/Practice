package com.zoo.concurrent;

import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculate  extends RecursiveTask<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2412770566046466764L;
	
	private long start;
	private long end;
	
	private static final long THRESHOLD=20000000;
	
	public ForkJoinCalculate(long start,long end) {
		this.start=start;
		this.end=end;
	}

	@Override
	protected Long compute() {
		long length=end - start;
		long sum=0L;
		if (length<=THRESHOLD) {//未超过阈值则直接计算
			for (long i = start; i <= end; i++) {
				sum+=i;
			}
		}else {//超过阈值拆分成两个任务
			long middle=(end+start)/2;//任务对半分
			ForkJoinCalculate left=new ForkJoinCalculate(start, middle);
			left.fork();//拆分成子线程，并压入线程队列
			ForkJoinCalculate right=new ForkJoinCalculate(middle+1, end);
			right.fork();
			sum=left.join()+right.join();
		}
		return sum;
	}

}
